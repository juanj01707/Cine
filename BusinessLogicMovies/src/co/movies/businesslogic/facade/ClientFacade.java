package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.ClientDTO;

public interface ClientFacade {
	
void create(ClientDTO dto);
	
	void update (ClientDTO dto);
	
	void delete (int id);
	
	List<ClientDTO> find(ClientDTO dto);
	
}
