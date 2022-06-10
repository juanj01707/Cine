package co.movies.dto;

public class PointOfSaleDTO {
	
	private int id;
	private String name;
	private String sede;
	private String direction;
	
	
	public PointOfSaleDTO(int id, String name, String sede, String direction) {
		super();
		this.id = id;
		this.name = name;
		this.sede = sede;
		this.direction = direction;
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
		this.name = name;
	}


	public String getSede() {
		return sede;
	}


	public void setSede(String sede) {
		this.sede = sede;
	}


	public String getDirection() {
		return direction;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	
	
	
	

	
	

}
