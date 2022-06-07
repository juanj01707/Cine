package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class VentaDTO {
	
	public VentaDTO(int id, String name, SedeDTO sede, String email) {
		super();
		this.id = id;
		this.name = name;
		this.sede = sede;
		this.email = email;
	}
	private int id;
	private String name;
	private SedeDTO sede;
	private String email;
	


	public void VentaDTO(int id, String name, SedeDTO sede, String email) {
		super();
		setId(id);
		setName(name);
		setSede(sede);
		setEmail(email);
		
	}
	
	public SedeDTO() {
		super();
		setName(UtilText.EMPTY);
		setSede(new SedeDTO());
		setEmail(UtilText.EMPTY);
		
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
	public SedeDTO getSede() {
		return sede;
	}
	public void setSede(SedeDTO sede) {
		this.sede = UtilObject.getUtilObject().getDefault(sede, new SedeDTO());
	
	public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = UtilText.getDefault(email);
		}


}