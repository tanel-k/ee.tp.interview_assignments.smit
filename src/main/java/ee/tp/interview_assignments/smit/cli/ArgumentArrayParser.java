package ee.tp.interview_assignments.smit.cli;

import ee.tp.interview_assignments.smit.cli.OptionDefinition.OptionDefinitionBuilder;
import ee.tp.interview_assignments.smit.cli.parsing.OptionParseException;
import ee.tp.interview_assignments.smit.cli.parsing.OptionParser;
import ee.tp.interview_assignments.smit.cli.validation.InvalidOptionException;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;
import ee.tp.interview_assignments.smit.utils.StringTableBuilder;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Utility class for converting input information encoded as a sequence of strings into a an instance of <code>T</code>.
 *
 * @param <T> extension of {@link OptionsBean} whose fields specify an input contract via {@link Option}.
 */
public class ArgumentArrayParser<T extends OptionsBean> {
    public static <T extends OptionsBean> ArgumentArrayParser<T> forClass(Class<T> optionsBeanClass) {
        Map<String, OptionDefinition> baseOptionMap = new LinkedHashMap<>();
        Map<String, OptionDefinition> aliasMap = new HashMap<>();
        Set<String> requiredOptionNames = new LinkedHashSet<>();

        for (Field field : optionsBeanClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Option.class)) {
                boolean isFlag = Boolean.class.isAssignableFrom(field.getType())
                    || boolean.class.isAssignableFrom(field.getType());
                Option optionSpec = field.getAnnotation(Option.class);
                OptionDefinition option = new OptionDefinitionBuilder()
                    .setName(optionSpec.name())
                    .setFlag(isFlag)
                    .setHelpOption(isFlag && optionSpec.helpOption())
                    .setValidatorClass(optionSpec.validator())
                    .setRequired(!isFlag && optionSpec.required())
                    .setAliases(Arrays.asList(optionSpec.aliases()))
                    .setUsage(optionSpec.description())
                    .setField(field)
                    .setParserClass(optionSpec.parser())
                    .build();

                field.setAccessible(true);
                baseOptionMap.put(optionSpec.name(), option);
                aliasMap.put(optionSpec.name(), option);

                for (String alias : optionSpec.aliases())
                    aliasMap.put(alias, option);

                if (option.isRequired())
                    requiredOptionNames.add(optionSpec.name());
            }
        }

        ArgumentArrayParser<T> parser = new ArgumentArrayParser<>();
        parser.optionsBeanClass = optionsBeanClass;
        parser.baseOptionMap = Collections.unmodifiableMap(baseOptionMap);
        parser.requiredOptionNames = Collections.unmodifiableSet(requiredOptionNames);
        parser.aliasMap = Collections.unmodifiableMap(aliasMap);
        return parser;
    }

    private String helpMessage;

    private Class<T> optionsBeanClass;
    private Collection<String> requiredOptionNames;
    private Map<String, OptionDefinition> aliasMap;
    private Map<String, OptionDefinition> baseOptionMap;

    private ArgumentArrayParser() { }

    /**
     * @param args List of argument strings.
     * @return instance of <code>T</code> whose fields have been instantiated according to information in the <code>args</code> parameter.
     * @throws ArgumentArrayParseException if arguments encoded in <code>args</code> do not conform to rules specified by <code>T</code>
     */
    public T parse(String... args) throws ArgumentArrayParseException {
        T optionsBean;
        try {
            optionsBean = this.optionsBeanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException(
                "Failed to instantiate option container '" + this.optionsBeanClass.getName() + "'.",
                ex
            );
        }

        boolean containsHelpOption = false;
        Set<String> remainingRequiredOptions = new LinkedHashSet<>(this.requiredOptionNames);

        class OptionPair {
            OptionDefinition option;
            String argument;
        }

        OptionPair lastOptionPair = null;
        List<OptionPair> optionPairs = new LinkedList<>();
        for (String arg : args) {
            if (aliasMap.containsKey(arg)) {
                OptionPair pair = new OptionPair();
                optionPairs.add(pair);
                pair.option = aliasMap.get(arg);
                lastOptionPair = pair;
            } else {
                if (lastOptionPair != null && lastOptionPair.argument == null) {
                    lastOptionPair.argument = arg;
                    lastOptionPair = null;
                } else {
                    throw new ArgumentArrayParseException("Unrecognized option '" + arg + "'.");
                }
            }
        }

        for (OptionPair optionPair : optionPairs) {
            OptionDefinition option = optionPair.option;
            if (option.isFlag()) {
                if (optionPair.argument != null)
                    throw new ArgumentArrayParseException("Flag '" + optionPair.option.getName() + "' cannot accept arguments.");

                containsHelpOption = containsHelpOption || option.isHelpOption();
                try {
                    option.getField().set(optionsBean, Boolean.TRUE);
                } catch (Throwable t) {
                    throw new RuntimeException(
                        "Failed to set flag '" + option.getName() + "'.",
                        t
                    );
                }
            } else {
                if (optionPair.argument == null)
                    throw new ArgumentArrayParseException("No value provided for '" + optionPair.option.getName() + "'.");

                Object optionValue;
                try {
                    OptionParser parser = option.getParserClass().newInstance();
                    optionValue = parser.parse(optionPair.argument);
                    option.getField().set(optionsBean, optionValue);
                } catch (OptionParseException ex) {
                    throw new ArgumentArrayParseException(
                        "Invalid argument for '" + option.getName() + "': " + ex.getMessage(), ex
                    );
                } catch (Throwable t) {
                    throw new RuntimeException("Failed to parse and set '" + option.getName() + "'.", t);
                }

                try {
                    OptionValidator validator = option.getValidatorClass().newInstance();
                    validator.validate(optionValue);
                } catch (InvalidOptionException ex) {
                    throw new ArgumentArrayParseException(
                        "Invalid argument for '" + option.getName() + "': " + ex.getMessage(),
                        ex
                    );
                } catch (Throwable t) {
                    throw new RuntimeException("Failed to validate option '" + option.getName() + "'.", t);
                }
            }

            remainingRequiredOptions.remove(option.getName());
        }

        if (!remainingRequiredOptions.isEmpty()) {
            if (args.length == 0 || containsHelpOption && args.length == 1) {
                optionsBean.setHelpRequest(true);
                return optionsBean;
            }

            String missingOptions = String.join(", ", remainingRequiredOptions);
            throw new ArgumentArrayParseException("Missing required options: " + missingOptions + ".");
        }

        return optionsBean;
    }

    /**
     * @return Usage instructions derived from the input option specification class.
     */
    public String getHelpMessage() {
        if (helpMessage == null) {
            StringBuilder messageBuf = new StringBuilder("Usage: ");
            StringTableBuilder optionTableBuf = new StringTableBuilder()
                .headers("Name", "Aliases", "Type", "Description");

            Iterator<OptionDefinition> optionIterator = baseOptionMap.values().iterator();
            while (optionIterator.hasNext()) {
                OptionDefinition option = optionIterator.next();
                messageBuf.append(option.getUsageString());

                if (optionIterator.hasNext())
                    messageBuf.append(" ");

                optionTableBuf.row(
                    option.getName(),
                    String.join(", ", option.getAliases()),
                    option.isFlag()
                        ? "N/A"
                        : StringUtils.capitalize(option.getField().getType().getSimpleName()),
                    option.getDescription()
                );
            }

            if (!optionTableBuf.isEmpty()) {
                messageBuf.append('\n').append("\nOptions:\n");
                messageBuf.append(optionTableBuf.build());
            }

            helpMessage = messageBuf.toString();
        }

        return helpMessage;
    }
}
