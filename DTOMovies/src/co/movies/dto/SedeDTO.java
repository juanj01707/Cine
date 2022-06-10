package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class SedeDTO {

	private int id;
	private String name;
	private CityDTO city;
	private String direction;


	public SedeDTO() {
		super();
		setId(id);
		setName(UtilText.EMPTY);
		setCity(new CityDTO());
		setDirection(UtilText.EMPTY);

	}

	public SedeDTO(int id, String name, CityDTO city, String direction
			) {
		super();
		setId(id);
		setName(name);
		setCity(city);
		setDirection(direction);

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
	public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = UtilObject.getUtilObject().getDefault(city, new CityDTO());
	}

	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = UtilText.getDefault(direction);
	}

public void validateName(List<String> validationMessages){
		
		validationMessages = UtilObject.getUtilObject().getDefault(validationMessages, new ArrayList<>());
		
		
		if(UtilText.isEmpty(getName())) {
			validationMessages.add("Name of sede is required!!!");
		} else if(UtilText.getDefault(getName()).length() > 50) {
			validationMessages.add("lenght of name of sede must be less o equals to 50 characters!!!");
		} else if(UtilText.getDefault(getName()).matches("^[a-zA-ZñÑáÁéÉíÍóÓúÚ ]*$")) {
			validationMessages.add("Name of sede contais invalid characters!!!");
		}
	}

}
