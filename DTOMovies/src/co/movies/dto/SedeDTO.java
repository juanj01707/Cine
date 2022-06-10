package co.movies.dto;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class SedeDTO {

	private short idSede;
	private String idNumber;
	private String name;
	private String city;
	private String direction;
	public short getIdSede() {
		return idSede;
		

<<<<<<< Updated upstream
		
	}
		
	public SedeDTO(short idSede, String idNumber, String name, String city, String direction) {
		super();
		this.idSede = idSede;
		this.idNumber = idNumber;
		this.name = name;
		this.city = city;
		this.direction = direction;
	}
	
=======

>>>>>>> Stashed changes
	public SedeDTO() {
		super();
		setName(UtilText.EMPTY);
		setIdSede(new SedeDTO());

		setDirection(UtilText.EMPTY);
	}
	
	public SedeDTO(int id, String name, CityDTO city, String direction
			) {
		super();
		setId(id);
		setName(name);
		setCity(city);

		setDirection(direction);

<<<<<<< Updated upstream

	public void setIdSede(short idSede) {
		this.idSede = idSede;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
=======
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
>>>>>>> Stashed changes
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
<<<<<<< Updated upstream
	public String getCity() {
		return city ;
	}
	public void setCity(String city) {
		this.city = UtilObject.getUtilObject().getDefault(city);
=======
	public CityDTO getCity() {
		return city;
	}
	public void setCity(CityDTO city) {
		this.city = UtilObject.getUtilObject().getDefault(city, new CityDTO());
>>>>>>> Stashed changes
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = UtilText.getDefault(direction);
	}
	
;

}
