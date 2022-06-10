package co.movies.api.controller.validator.sede;

import java.util.ArrayList;
import java.util.List;

import co.movies.api.controller.validator.Validator;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.dto.SedeDTO;

public class CreatedSedeValidator implements Validator<SedeDTO>{
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(SedeDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("It's not possible validate sede data ");
		}

		dto.validateName(validationMessages);

		return validationMessages;
	}

}
