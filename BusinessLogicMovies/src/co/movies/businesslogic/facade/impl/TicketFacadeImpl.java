package co.movies.businesslogic.facade.impl;

import java.util.List;

import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.data.factory.DAOFactory;

public class TicketFacadeImpl implements TicketFacade  {
	
	private DAOFactory daoFactory = DAOFactory.getDaoFactory();

	public void create(TicketDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			TicketBusiness ticketBusiness = new TicketBusinessImpl(daoFactory);
			ticketBusiness.create(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to create the new Ticket on create method in TicketFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void update(TicketDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			TicketBusiness ticketBusiness = new ticketBusinessImpl(daoFactory);
			TicketBusiness.update(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to update the new Ticket on update method in TIcketFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void delete(int id) {
		try {
			daoFactory.initTransaction();

			TicketBusiness ticketBusiness = new TicketBusinessImpl(daoFactory);
			TicketBusiness.delete(id);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to delete the new Ticket on delete method in TicketFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public List<TicketDTO> find(TicketDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			TicketBusiness ticketBusiness = new TicketBusinessImpl(daoFactory);
			return ticketBusiness.find(dto);

		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to find the new Ticket on find method in TicketFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}

	}

}
