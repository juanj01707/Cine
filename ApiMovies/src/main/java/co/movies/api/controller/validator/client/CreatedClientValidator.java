package co.movies.api.controller.validator.client;

import java.util.ArrayList;
import java.util.List;

import co.movies.api.controller.validator.Validator;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.dto.ClientDTO;

public class CreatedClientValidator implements Validator<ClientDTO> {

	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(ClientDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("It's not possible validate client data ");
		}

		dto.validateName(validationMessages);

		return validationMessages;

	}

}
