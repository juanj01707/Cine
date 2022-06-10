package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.SalaDTO;

public interface SalaFacade {

void create(SalaDTO dto);
	
	void update (SalaDTO dto);
	
	void delete (int id);
	
	List<SalaDTO> find(SalaDTO dto);


}
