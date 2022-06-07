package co.movies.data.factory;

import java.sql.Connection;

import co.movies.data.dao.SaleDAO;

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
	
	public abstract SaleDAO getSaleDAO();

}
