package co.movies.data.factory;

import java.sql.Connection;

import co.movies.data.dao.CityDAO;
import co.movies.data.dao.ClientDAO;
import co.movies.data.dao.IdTypeDAO;
import co.movies.data.dao.ProductDAO;
import co.movies.data.dao.SalaDAO;
import co.movies.data.dao.SaleDAO;
import co.movies.data.dao.SedeDAO;
import co.movies.data.dao.StockDAO;
import co.movies.data.dao.TicketDAO;
import co.movies.data.factory.azuresql.AzureSqlDAOFactory;

public abstract class DAOFactory {

	public static DAOFactory getDaoFactory() {
		return AzureSqlDAOFactory.create();
	}

	protected abstract void openConnection();

	protected abstract Connection getConnection();

	public abstract void initTransaction();

	public abstract void closeConnection();

	public abstract void commitTransaction();

	public abstract void rollbackTransaction();

	public abstract CityDAO getCityDAO();

	public abstract ClientDAO getClientDAO();

	public abstract IdTypeDAO getIdType();

	public abstract ProductDAO getProductDAO();

	public abstract SalaDAO getSalaDAO();

	public abstract SaleDAO getSaleDAO();

	public abstract SedeDAO getSedeDAO();

	public abstract TicketDAO getTicketDAO();

}
