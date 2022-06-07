package co.movies.data.factory.azuresql;

import java.sql.Connection;

import co.movies.data.dao.SaleDAO;
import co.movies.data.factory.DAOFactory;

public class AzureSqlDAOFactory extends DAOFactory {

	@Override
	protected void openConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Connection getConnection() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void commitTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rollbackTransaction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SaleDAO getSaleDAO() {
		// TODO Auto-generated method stub
		return null;
	}

}
