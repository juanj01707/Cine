import java.util.List;

import co.movies.dto.IdTypeDTO;
import co.movies.busineelogic.business.IdTypeBusiness;
import co.movies.busineelogic.business.impl.IdTypeBusinessImpl;
import co.movies.busineelogic.facade.IdTypeFacade;
import co.movies.crosscutting.exception.GradesException;
import co.movies.data.factory.DAOFactory;

public class IdTypeFacadeImpl implements IdTypeFacade {

	private DAOFactory daoFactory = DAOFactory.getDaoFactory();

	@Override
	public void create(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.create(dto);
			daoFactory.commitTransaction();
		} catch (GradesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			String message = "Thegre was a problme trying to create the new idType on create method in idTypeFacadeImpl ";
			throw GradesException.buildTechnicalBusinessLogicException(message);
		}finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public void update(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.update(dto);
			daoFactory.commitTransaction();
		} catch (GradesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			String message = "Thegre was a problme trying to update the method in idTypeFacadeImpl ";
			throw GradesException.buildTechnicalBusinessLogicException(message);
		}finally {
			daoFactory.closeConnection();
		}
		
	}

	

	@Override
	public void delete(int id) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();
			
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.delete(id);
			daoFactory.commitTransaction();
		} catch (GradesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			String message = "Thegre was a problme trying to delete the method in idTypeFacadeImpl ";
			throw GradesException.buildTechnicalBusinessLogicException(message);
		}finally {
			daoFactory.closeConnection();
		}
		
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			return idTypeBusiness.find(dto);
		} catch (GradesException exception) {
			throw exception;
		} catch (Exception exception) {
			
			String message = "Thegre was a problme trying to find  method in idTypeFacadeImpl ";
			throw GradesException.buildTechnicalBusinessLogicException(message);
		}finally {
			daoFactory.closeConnection();
		}
	}
}