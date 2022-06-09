package co.movies.data.dao;

import java.util.List;

import co.movies.dto.CityDTO;


public interface CityDAO {
	void create(CityDTO city);
	void update(CityDTO city);
	void delete(int id);
	List<CityDTO> find(CityDTO city);
}
