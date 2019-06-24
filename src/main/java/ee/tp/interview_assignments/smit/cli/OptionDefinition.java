package ee.tp.interview_assignments.smit.cli;

import ee.tp.interview_assignments.smit.cli.parsing.CommandLineOptionParser;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class OptionDefinition {
	static class CommandLineOptionDefinitionBuilder {
		private OptionDefinition option;

		CommandLineOptionDefinitionBuilder() {
			this.option = new OptionDefinition();
		}

		public CommandLineOptionDefinitionBuilder setName(String name) {
			this.option.name = name;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setRequired(boolean required) {
			this.option.required = required;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setFlag(boolean flag) {
			this.option.flag = flag;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setUsage(String usage) {
			this.option.description = usage;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setParserClass(Class<? extends CommandLineOptionParser> parserClass) {
			this.option.parserClass = parserClass;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setField(Field field) {
			this.option.field = field;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setHelpOption(boolean helpOption) {
			this.option.helpOption = helpOption;
			return this;
		}

		public CommandLineOptionDefinitionBuilder setAliases(List<String> aliases) {
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
	private Set<String> aliases = new LinkedHashSet<>();
	private Class<? extends CommandLineOptionParser> parserClass;

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

	public Class<? extends CommandLineOptionParser> getParserClass() {
		return parserClass;
	}

	public Field getField() {
		return field;
	}

	public Set<String> getAliases() {
		return Collections.unmodifiableSet(aliases);
	}
}
