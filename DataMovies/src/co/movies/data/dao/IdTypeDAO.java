package co.movies.data.dao;

import java.util.List;

import co.movies.dto.IdTypeDTO;

public interface IdTypeDAO {
	
	void create(IdTypeDTO idType);

	void update(IdTypeDTO idType);

	void delete(int id);

	List<IdTypeDTO> find(IdTypeDTO idType);
	

}
