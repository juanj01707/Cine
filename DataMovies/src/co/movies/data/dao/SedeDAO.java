package co.movies.data.dao;

import java.util.List;

import co.movies.dto.SedeDTO;

public interface SedeDAO {
	void create(SedeDTO campus);
	void update(SedeDTO campus);
	void delete(int id);
	List<SedeDTO> Find(SedeDTO campus);
}
