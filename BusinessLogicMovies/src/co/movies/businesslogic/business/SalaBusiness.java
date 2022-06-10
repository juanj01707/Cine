package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.SalaDTO;

public interface SalaBusiness {

void create(SalaDTO dto);
	
	void update (SalaDTO dto);
	
	void delete (int id);
	
	List<SalaDTO> find(SalaDTO dto);


}
