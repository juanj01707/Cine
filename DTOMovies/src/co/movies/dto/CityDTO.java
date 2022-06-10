package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class CityDTO {

	private int id;
	private String name;

	public CityDTO() {
		super();
		setName(UtilText.EMPTY);
	}

	public CityDTO(int id, String name) {
		super();
		setId(id);
		setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = UtilText.getDefault(name);
	}

	public void validateName(List<String> validationMessages) {

		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());

		if (UtilText.isEmpty(getName())) {
			validationMessages.add("Name of city is required!!!");
		} else if (UtilText.getDefault(getName()).length() > 50) {
			validationMessages.add("lenght of name of city must be less o equals to 50 characters!!!");
		} else if (UtilText.getDefault(getName()).matches("^[a-zA-ZñÑáÁéÉíÍóÓúÚ ]*$")) {
			validationMessages.add("Name of city contais invalid characters!!!");
		}
	}

}
