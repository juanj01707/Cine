package co.movies.businesslogic.business;
import java.util.List;

import co.movies.dto.IdTypeDTO;

public interface IdTypeBusiness {
	
	void create(IdTypeDTO dto);
	
	void update (IdTypeDTO dto);
	
	void delete (int id);
	
	List<IdTypeDTO> find(IdTypeDTO dto);
}