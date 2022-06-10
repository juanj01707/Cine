package co.movies.businesslogic.facade.impl;

import java.util.List;

import co.movies.businesslogic.business.IdTypeBusiness;
import co.movies.businesslogic.business.impl.IdTypeBusinessImpl;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.data.factory.DAOFactory;
import co.movies.dto.IdTypeDTO;

public class SalaFacadeImpl implements SalaFacade {
	private DAOFactory daoFactory = DAOFactory.getDaoFactory();

	public void create(SalaDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			SalaBusiness salaBusiness = new SalaBusinessImpl(daoFactory);
			salaBusiness.create(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to create the new Sala on create method in SalaFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void update(SalaDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			SalaBusiness salaBusiness = new SalaBusinessImpl(daoFactory);
			SalaBusiness.update(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to update the new Sala on update method in SalaFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void delete(int id) {
		try {
			daoFactory.initTransaction();

			SalaBusiness salaBusiness = new SalaBusinessImpl(daoFactory);
			salaBusiness.delete(id);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to delete the new Sala on delete method in SalaFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public List<SalaDTO> find(SalaDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			SalaBusiness salaBusiness = new SalaBusinessImpl(daoFactory);
			return salaBusiness.find(dto);

		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to find the new Sala on find method in SalaFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}

	}


}
