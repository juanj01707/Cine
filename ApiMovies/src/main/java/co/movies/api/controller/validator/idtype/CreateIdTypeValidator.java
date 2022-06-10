package co.movies.api.controller.validator.idtype;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.api.controller.validator.Validator;
import co.movies.dto.IdTypeDTO;

public class CreateIdTypeValidator implements Validator<IdTypeDTO> {
	
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(IdTypeDTO dto) {
		if(UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("It's not possible validate Id Type data ");
		}
		
		dto.validateName(validationMessages);
		
		return validationMessages;
	}
	
	

}
