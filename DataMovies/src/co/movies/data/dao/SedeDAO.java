package co.movies.data.dao;

import java.util.List;

import co.movies.dto.SedeDTO;

public interface SedeDAO {
	void create(SedeDTO sede);
	void update(SedeDTO sede);
	void delete(int id);
	List<SedeDTO> Find(SedeDTO sede);
}
