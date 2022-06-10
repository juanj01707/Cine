package co.movies.api.controller.validator.city;

import java.util.ArrayList;
import java.util.List;

import co.movies.api.controller.validator.Validator;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.dto.CityDTO;

public class CreatedCityValidator implements Validator<CityDTO> {

	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(CityDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("It's not possible validate city data ");
		}

		dto.validateName(validationMessages);

		return validationMessages;

	}
}
