package co.movies.api.controller.validator.product;

import java.util.ArrayList;
import java.util.List;

import co.movies.api.controller.validator.Validator;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.dto.ProductDTO;

public class CreatedProductValidator implements Validator<ProductDTO>{
	private List<String> validationMessages = new ArrayList<>();

	@Override
	public List<String> validate(ProductDTO dto) {
		if (UtilObject.getUtilObject().isNull(dto)) {
			validationMessages.add("It's not possible validate product data ");
		}

		dto.validateName(validationMessages);

		return validationMessages;

	}

}
