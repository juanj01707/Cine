package co.movies.data.dao;

import java.util.List;

import co.movies.dto.SalaDTO;

public interface SalaDAO {
	
	void create(SalaDTO sala);

	void update(SalaDTO sala);

	void delete(int salaId);

	List<SalaDTO> find(SalaDTO sala);

}
