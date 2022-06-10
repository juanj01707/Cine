package co.movies.businesslogic.facade.impl;

import java.util.List;

import co.movies.businesslogic.business.ProductBusiness;
import co.movies.businesslogic.business.impl.ProductBusinessImpl;
import co.movies.businesslogic.facade.ProductFacade;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.data.factory.DAOFactory;
import co.movies.dto.ProductDTO;

public class ProductFacadeImpl implements ProductFacade {
	private DAOFactory daoFactory = DAOFactory.getDaoFactory();

	public void create(ProductDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			ProductBusiness ProductBusiness = new ProductBusinessImpl(daoFactory);
			ProductBusiness.create(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to create the new Product on create method in IdTypeFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void update(ProductDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			daoFactory.initTransaction();

			ProductBusiness productBusiness = new ProductBusinessImpl(daoFactory);
			productBusiness.update(dto);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to update the new Product on update method in ProductFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public void delete(int id) {
		try {
			daoFactory.initTransaction();

			ProductBusiness productBusiness = new ProductBusinessImpl(daoFactory);
			productBusiness.delete(id);

			daoFactory.commitTransaction();
		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to delete the new Product on delete method in ProductFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}
	}

	@Override
	public List<ProductDTO> find(ProductDTO dto) {
		DAOFactory daoFactory = DAOFactory.getDaoFactory();
		try {
			ProductBusiness productBusiness = new ProductBusinessImpl(daoFactory);
			return productBusiness.find(dto);

		} catch (MoviesException exception) {
			daoFactory.rollbackTransaction();
			throw exception;
		} catch (Exception exception) {
			daoFactory.rollbackTransaction();
			var message = "there was a problem trying to find the new Product on find method in ProductFacadeImpl";
			throw MoviesException.buildTechnicalBusinessLogicException(message);
		} finally {
			daoFactory.closeConnection();
		}

	}


}
