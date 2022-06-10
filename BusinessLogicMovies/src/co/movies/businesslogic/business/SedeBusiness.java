package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.SedeDTO;

public interface SedeBusiness {
	void create(SedeDTO dto);
	
	void update (SedeDTO dto);
	
	void delete (int id);
	
	List<SedeDTO> find(SedeDTO dto);



}
