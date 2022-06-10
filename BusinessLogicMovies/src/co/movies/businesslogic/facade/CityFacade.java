package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.CityDTO;

public interface CityFacade {
	
void create(CityDTO dto);
	
	void update (CityDTO dto);
	
	void delete (int id);
	
	List<CityDTO> find(CityDTO dto);
	
}
