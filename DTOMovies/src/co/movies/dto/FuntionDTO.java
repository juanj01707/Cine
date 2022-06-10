package co.movies.dto;


public class FuntionDTO {

	private int id;
	private String name;
	private ClientDTO client;
	

	
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