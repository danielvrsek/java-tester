package dev.vrsek.javatester.core.source.builders;

import dev.vrsek.javatester.core.source.builders.model.AccessModifier;
import dev.vrsek.utils.exceptions.ValidationException;
import dev.vrsek.utils.validators.IValidator;
import dev.vrsek.utils.validators.NotNullObjectValidator;
import dev.vrsek.utils.validators.NotNullOrEmptyStringValidator;

public class JavaFieldSourceBuilder implements IMemberSourceBuilder {
	private AccessModifier accessModifier;
	private String typeName;
	private String name;
	private IValidator[] validators;

	public JavaFieldSourceBuilder() {
		accessModifier = AccessModifier.PUBLIC;
		initializeValidators();
	}

	private void initializeValidators(){
		validators = new IValidator[] {
				new NotNullOrEmptyStringValidator(() -> name, "Field name cannot be null or empty."),
				new NotNullOrEmptyStringValidator(() -> typeName, "Field's type cannot be null or empty."),
				new NotNullObjectValidator(() -> accessModifier, "Field's access modifier cannot be null.")
		};
	}

	@Override
	public void setAccessModifier(AccessModifier accessModifier) {

		this.accessModifier = accessModifier;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setTypeName(String typeName) {

		this.typeName = typeName;
	}

	@Override
	public String build() throws ValidationException {
		validate();

		return String.format("%s %s %s;", accessModifier, typeName, name);
	}

	private void validate() throws ValidationException {
		for (IValidator validator : validators) {
			validator.validate();
		}
	}
}
