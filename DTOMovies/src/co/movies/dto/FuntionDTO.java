package co.movies.dto;

<<<<<<< Updated upstream
import java.util.List;

import co.movies.crosscutting.util.numeric.UtilNumeric;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class FuncionDTO {
=======
public class FuntionDTO {

	private int id;
	private String name;
	private ClientDTO client;
	
>>>>>>> Stashed changes
	
	private int id;
	private String name;
	private ClientDTO client;
	public int getId() {
		return id;
		
		
		
	}
	public FuncionDTO(int id, String name, ClientDTO client) {
		super();
		this.id = id;
		this.name = name;
		this.client = client;
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
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	
	public FuntionDTO() {
		super();
		setId(id);
	}

	public FuntionDTO(int id, String name, ClientDTO client) {
		super();
		this.id = id;
		this.name = name;
		this.client = client;
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
	public ClientDTO getClient() {
		return client;
	}
	public void setClient(ClientDTO client) {
		this.client = client;
	}
	
	
	
}