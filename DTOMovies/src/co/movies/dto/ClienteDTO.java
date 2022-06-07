package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class ClienteDTO {
	
	private int id;
	private String name;
	private IdTypeDTO idType;
	private String idNumber;
	private List<FuncionDTO> funcion;
	
	
	
	public ClienteDTO() {
		super();
		setName(UtilText.EMPTY);
		setIdNumber(UtilText.EMPTY);
		setIdType(new IdTypeDTO());
		setFuncion(new ArrayList<FuncionDTO>());
	}
	
	public ClienteDTO(int id, String name, IdTypeDTO idType, String idNumber, List<FuncionDTO> funcion) {
		super();
		setId(id);
		setName(name);
		setIdNumber(idNumber);
		setIdType(idType);
		setFuncion(funcion);
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
	public IdTypeDTO getIdType() {
		return idType;
	}
	public void setIdType(IdTypeDTO idType) {
		this.idType = UtilObject.getUtilObject().getDefault(idType, new IdTypeDTO());
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = UtilText.getDefault(idNumber);
	}
	public List<FuncionDTO> getFuncion() {
		return funcion;
	}
	public void setFuncion(List<FuncionDTO> funcion) {
		this.funcion = UtilObject.getUtilObject().getDefault(funcion, new ArrayList<FuncionDTO>());
	}
	
	
	
}
