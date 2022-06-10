package co.movies.api.controller.validator;

import java.util.List;

public interface Validator<D> {
	
	List<String> validate(D dto);
}
