package co.movies.dto;

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
	
	
}
