package co.movies.businesslogic.business.impl;

import java.util.List;

import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.businesslogic.business.IdTypeBusiness;

import co.movies.data.factory.DAOFactory;
import co.movies.dto.IdTypeDTO;

public class IdTypeBusinessImpl implements IdTypeBusiness{
	
	private DAOFactory daoFactory;
	
	public IdTypeBusinessImpl(DAOFactory daoFactory) {
		if (UtilObject.getUtilObject().isNull(daoFactory)) {
			throw MoviesException.buildTechnicalBusinessLogicException("It's not possible create a IdTypeBusinessImpl when the DAOFactory is null");
		}
		this.daoFactory = daoFactory;
	}

	
	@Override
	public void create(IdTypeDTO dto) {
		validateIdTypeDoesNotExistWhithSameName(dto);
		daoFactory.getIdTypeDAO().create(dto);
	}
	
	public void validateIdTypeDoesNotExistWhithSameName(IdTypeDTO dto) {
		
		
		IdTypeDTO dtoValidator = new IdTypeDTO();
		dtoValidator.setName(dto.getName());
		
		List<IdTypeDTO> list =  daoFactory.getIdTypeDAO().find(dtoValidator);
		
		if (!list.isEmpty()) {
			var message = "An Id Type With the same name alredy Exist!";
		}
			throw MoviesException.buildBusinessLogicException(null);
		}
		

	@Override
	public void update(IdTypeDTO dto) {
		daoFactory.getIdTypeDAO().update(dto);
	}

	@Override
	public void delete(int id) {
		daoFactory.getIdTypeDAO().delete(id);
		
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		return daoFactory.getIdTypeDAO().find(dto);
	}

}
