package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.CityDTO;

public interface CityBusiness {
	
void create(CityDTO dto);
	
	void update (CityDTO dto);
	
	void delete (int id);
	
	List<CityDTO> find(CityDTO dto);
	
}
