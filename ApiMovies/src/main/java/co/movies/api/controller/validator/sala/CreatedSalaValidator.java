package co.movies.api.controller.validator.sala;

import java.util.ArrayList;
import java.util.List;

import co.movies.api.controller.validator.Validator;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.dto.SalaDTO;

public class CreatedSalaValidator implements Validator<SalaDTO>{
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(SalaDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("It's not possible validate sala data ");
		}

		dto.validateName(validationMessages);

		return validationMessages;
	}

}
