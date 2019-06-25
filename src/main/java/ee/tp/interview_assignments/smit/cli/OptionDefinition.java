package ee.tp.interview_assignments.smit.cli;

import ee.tp.interview_assignments.smit.cli.parsing.OptionParser;
import ee.tp.interview_assignments.smit.cli.validation.OptionValidator;
import ee.tp.interview_assignments.smit.utils.StringUtils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Container for parameters extracted from {@link Option}.
 */
class OptionDefinition {
    static class OptionDefinitionBuilder {
        private OptionDefinition option;
        private Class<? extends OptionValidator> validator;

        OptionDefinitionBuilder() {
            this.option = new OptionDefinition();
        }

        public OptionDefinitionBuilder setName(String name) {
            this.option.name = name;
            return this;
        }

        public OptionDefinitionBuilder setRequired(boolean required) {
            this.option.required = required;
            return this;
        }

        public OptionDefinitionBuilder setFlag(boolean flag) {
            this.option.flag = flag;
            return this;
        }

        public OptionDefinitionBuilder setUsage(String usage) {
            this.option.description = usage;
            return this;
        }

        public OptionDefinitionBuilder setParserClass(Class<? extends OptionParser> parserClass) {
            this.option.parserClass = parserClass;
            return this;
        }

        public OptionDefinitionBuilder setValidatorClass(Class<? extends OptionValidator> validatorClass) {
            this.option.validatorClass = validatorClass;
            return this;
        }

        public OptionDefinitionBuilder setField(Field field) {
            this.option.field = field;
            return this;
        }

        public OptionDefinitionBuilder setHelpOption(boolean helpOption) {
            this.option.helpOption = helpOption;
            return this;
        }

        public OptionDefinitionBuilder setAliases(List<String> aliases) {
            this.option.aliases.addAll(aliases);
            return this;
        }

        public OptionDefinition build() {
            return this.option;
        }
    }

    private boolean flag;
    private boolean required;
    private boolean helpOption;

    private String name;
    private String description;

    private Field field;

    private Class<? extends OptionValidator> validatorClass;
    private Class<? extends OptionParser> parserClass;

    private Set<String> aliases = new LinkedHashSet<>();

    private OptionDefinition() { }

    public boolean isFlag() {
        return flag;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public String getDescription() {
        return description;
    }

    public boolean isHelpOption() {
        return helpOption;
    }

    public Class<? extends OptionParser> getParserClass() {
        return parserClass;
    }

    public Field getField() {
        return field;
    }

    public Set<String> getAliases() {
        return Collections.unmodifiableSet(aliases);
    }

    public Class<? extends OptionValidator> getValidatorClass() {
        return validatorClass;
    }

    public String getUsageString() {
        String usageString = flag
            ? name
            : name + " <" + StringUtils.capitalize(field.getType().getSimpleName()) + ">";
        return required
            ? usageString
            : "(" + usageString + ")";
    }
}
