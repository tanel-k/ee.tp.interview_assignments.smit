package ee.tp.interview_assignments.smit.cli;

import ee.tp.interview_assignments.smit.cli.OptionDefinition.CommandLineOptionDefinitionBuilder;
import ee.tp.interview_assignments.smit.cli.parsing.OptionParseException;
import ee.tp.interview_assignments.smit.cli.parsing.CommandLineOptionParser;
import ee.tp.interview_assignments.smit.cli.validation.InvalidOptionException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommandLineArgArrayParser<T extends OptionsBean> {
	public static <T extends OptionsBean> CommandLineArgArrayParser<T> forClass(Class<T> optionContainerClass) {
		Map<String, OptionDefinition> nameToOptionMap = new HashMap<>();
		Set<String> baseOptionNames = new LinkedHashSet<>();
		Set<String> requiredOptionNames = new LinkedHashSet<>();

		for (Field field : optionContainerClass.getDeclaredFields()) {
			if (field.isAnnotationPresent(Option.class)) {
				boolean isFlag = Boolean.class.isAssignableFrom(field.getType())
						|| boolean.class.isAssignableFrom(field.getType());
				Option optionSpec = field.getAnnotation(Option.class);
				OptionDefinition option = new CommandLineOptionDefinitionBuilder()
						.setName(optionSpec.name())
						.setFlag(isFlag)
						.setHelpOption(isFlag && optionSpec.helpOption())
						.setRequired(!isFlag && optionSpec.required())
						.setAliases(Arrays.asList(optionSpec.aliases()))
						.setUsage(optionSpec.description())
						.setField(field)
						.setParserClass(optionSpec.parser())
						.build();

				field.setAccessible(true);
				baseOptionNames.add(optionSpec.name());
				nameToOptionMap.put(optionSpec.name(), option);

				for (String alias : optionSpec.aliases())
					nameToOptionMap.put(alias, option);

				if (option.isRequired())
					requiredOptionNames.add(optionSpec.name());
			}
		}

		CommandLineArgArrayParser<T> parser = new CommandLineArgArrayParser<>();
		parser.optionContainerClass = optionContainerClass;
		parser.baseOptionNames = Collections.unmodifiableSet(baseOptionNames);
		parser.requiredOptionNames = Collections.unmodifiableSet(requiredOptionNames);
		parser.optionNameMap = Collections.unmodifiableMap(nameToOptionMap);
		return parser;
	}

	private String helpMessage;

	private Class<T> optionContainerClass;
	private Collection<String> baseOptionNames;
	private Collection<String> requiredOptionNames;
	private Map<String, OptionDefinition> optionNameMap;

	private CommandLineArgArrayParser() { }

	public String getHelpMessage() {
		if (helpMessage == null) {
			StringBuilder messageBuf = new StringBuilder();

			messageBuf.append("Usage: ");

			int[] colLengths = new int[] {"Name ".length(), "Aliases ".length(), "Type ".length(), "Description ".length()};
			List<List<String>> rows = new ArrayList<>(baseOptionNames.size());
			Iterator<String> baseOptionNameIter = baseOptionNames.iterator();
			// FIXME: extract table printing logic
			while (baseOptionNameIter.hasNext()) {
				OptionDefinition option = optionNameMap.get(baseOptionNameIter.next());
				if (!option.isRequired())
					messageBuf.append('(');

				messageBuf.append(option.getName());
				if (!option.isFlag())
					messageBuf.append(' ')
						.append('<')
						.append(option.getField().getType().getSimpleName())
						.append('>');

				if (!option.isRequired())
					messageBuf.append(')');

				if (baseOptionNameIter.hasNext())
					messageBuf.append(" ");

				List<String> row = new ArrayList<>(4);
				String nameCell = option.getName() + " ";
				String aliasCell = String.join(" ", option.getAliases()) + " ";
				String typeCell = option.getField().getType().getSimpleName() + " ";
				String descriptionCell = option.getDescription() + " ";

				row.add(nameCell);
				row.add(aliasCell);
				row.add(typeCell);
				row.add(descriptionCell);
				rows.add(row);

				colLengths[0] = Math.max(colLengths[0], nameCell.length());
				colLengths[1] = Math.max(colLengths[1], aliasCell.length());
				colLengths[2] = Math.max(colLengths[2], typeCell.length());
				colLengths[3] = Math.max(colLengths[3], descriptionCell.length());
			}

			messageBuf.append('\n').append("\nOptions:\n");
			messageBuf.append(padRight("Name ", colLengths[0]));
			messageBuf.append(padRight("Aliases ", colLengths[1]));
			messageBuf.append(padRight("Type ", colLengths[2]));
			messageBuf.append(padRight("Description ", colLengths[3]));
			messageBuf.append('\n');

			for (List<String> row : rows) {
				for (int i = 0; i < row.size(); i++) {
					messageBuf.append(padRight(row.get(i), colLengths[i]));
				}
				messageBuf.append('\n');
			}

			helpMessage = messageBuf.toString();
		}

		return helpMessage;
	}

	private static String padRight(String s, int width) {
		return String.format("%-" + width + "s", s);
	}

	public T parse(String... args) throws InvalidOptionException, InvalidOptionsException {
		// FIXME: This looks stinky
		boolean containsHelpOption = false;
		Set<String> remainingRequiredOptions = new LinkedHashSet<>(this.requiredOptionNames);

		T optionBean;
		try {
			optionBean = this.optionContainerClass.newInstance();
		} catch (InstantiationException|IllegalAccessException ex) {
			throw new RuntimeException(
					"Failed to instantiate option container '" + this.optionContainerClass.getName() + "'.",
					ex
			);
		}

		OptionDefinition lastOption = null;
		for (String arg : args) {
			if (lastOption != null) {
				CommandLineOptionParser parser;
				try {
					parser = lastOption.getParserClass().newInstance();
				} catch (InstantiationException|IllegalAccessException ex) {
					throw new RuntimeException(
						"Failed to instantiate parser for option '" + lastOption.getName() + "'.",
						ex
					);
				}

				Object parseResult;
				try {
					parseResult = parser.parse(arg);
				} catch (OptionParseException ex) {
					throw new InvalidOptionsException(ex.getMessage(), ex);
				}

				try {
					lastOption.getField().set(optionBean, parseResult);
				} catch (IllegalAccessException ex) {
					throw new RuntimeException(
						"Failed to set option '" + lastOption.getName() + "'.",
						ex
					);
				}

				remainingRequiredOptions.remove(lastOption.getName());
				lastOption = null;
			} else {
				if (this.optionNameMap.containsKey(arg)) {
					OptionDefinition option = this.optionNameMap.get(arg);
					if (option.isFlag()) {
						containsHelpOption = containsHelpOption || option.isHelpOption();

						// FIXME: duplication
						try {
							option.getField().set(optionBean, Boolean.TRUE);
						} catch (IllegalAccessException ex) {
							throw new RuntimeException(
									"Failed to set flag '" + option.getName() + "'.",
									ex
							);
						}
					} else {
						lastOption = this.optionNameMap.get(arg);
					}
				} else {
					throw new InvalidOptionsException("Unknown option '" + arg + "'.");
				}
			}
		}

		if (!remainingRequiredOptions.isEmpty()) {
			if (args.length == 0 || containsHelpOption && args.length == 1) {
				optionBean.setHelpRequest(true);
				return optionBean;
			}

			String missingOptions = String.join(", ", remainingRequiredOptions);
			throw new InvalidOptionsException("Missing required options: " + missingOptions + ".");
		}

		return optionBean;
	}
}
