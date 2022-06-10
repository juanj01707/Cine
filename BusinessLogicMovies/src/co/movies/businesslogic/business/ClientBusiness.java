package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.ClientDTO;

public interface ClientBusiness {
	
void create(ClientDTO dto);
	
	void update (ClientDTO dto);
	
	void delete (int id);
	
	List<ClientDTO> find(ClientDTO dto);
	
}
