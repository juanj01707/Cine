package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.SedeDTO;

public interface SedeFacade {
	void create(SedeDTO dto);
	
	void update (SedeDTO dto);
	
	void delete (int id);
	
	List<SedeDTO> find(SedeDTO dto);



}
