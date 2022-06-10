package co.movies.businesslogic.facade.impl;

import java.util.List;

import co.movies.businesslogic.business.IdTypeBusiness;
import co.movies.businesslogic.business.impl.IdTypeBusinessImpl;
import co.movies.businesslogic.facade.IdTypeFacade;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.data.factory.DAOFactory;
import co.movies.dto.IdTypeDTO;

public class IdTypeFacadeImpl implements IdTypeFacade {

	private DAOFactory daoFactory = DAOFactory.getDaoFactory();

	public void create(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.create(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to create the new IdType on create method in IdTypeFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
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
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to update the new IdType on update method in IdTypeFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void delete(int id) {
		try {
			daoFactory.initTransaction();

			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			idTypeBusiness.delete(id);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to delete the new IdType on delete method in IdTypeFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			IdTypeBusiness idTypeBusiness = new IdTypeBusinessImpl(daoFactory);
			return idTypeBusiness.find(dto);

		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to find the new IdType on find method in IdTypeFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}

	}

}
