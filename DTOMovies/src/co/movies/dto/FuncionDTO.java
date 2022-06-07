package co.movies.dto;

import java.util.ArrayList;
import java.util.List;

import co.movies.util.numeric.UtilNumeric;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;

public class FuncionDTO {
	private int id;
	private String name;
	private int precioFuncion;
	private FuncionTypeDTO funcionType;
	private SedeDTO sede;
	private ClienteDTO cliente;
	private List<MekatoDTO> Mekato;
	
	
	
	
	
	public FuncionDTO(int id, String name, int precioFuncion,
			int priceQuotation, FuncionTypeDTO funcionType, SedeDTO sede, ClienteDTO cliente,
			List<MekatoDTO> mekato) {
		super();
		setSede(sede);
		setCliente(cliente);
		setId(id);
		setName(name);
		setPrecioFuncion(precioFuncion);
		setFuncionType(quotationType);
		setMekato(mekato);
		
	}
	
	public FuncionDTO() {
		super();
		setSede(new SedeDTO());
		setCliente(new ClienteDTO());
		setPrecioFuncion(0);
		setMekato(new ArrayList<MekatoDTO>());
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
	public int getPrecioFuncion() {
		return precioFuncion;
	}
	public void setPrecioFuncion(int precioFuncion) {
		this.precioFuncion = (int) UtilNumeric.getUtilNumeric().getDefault(precioFuncion);
	}
	public FuncionTypeDTO getFuncionType() {
		return funcionType;
	}
	public void setFuncionType(FuncionTypeDTO funcionType) {
		this.funcionType = UtilObject.getUtilObject().getDefault(funcionType, new FuncionTypeDTO());
	}
	public SedeDTO getSede() {
		return sede;
	}
	public void setSede(SedeDTO sede) {
		this.sede = UtilObject.getUtilObject().getDefault(sede, new sedeDTO());
	}
	public ClienteDTO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteDTO cliente) {
		this.cliente = UtilObject.getUtilObject().getDefault(cliente, new ClienteDTO());
	}
	public List<MekatoDTO> getMekato() {
		return mekato;
	}
	public void setRawMaterials(List<MekatoDTO> mekato) {
		this.mekato = UtilObject.getUtilObject().getDefault(mekato, new ArrayList<MekatoDTO>());
	}
	
	
}