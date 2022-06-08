package co.movies.data.dao.azuresql;

import java.sql.Connection;

import co.movies.data.dao.SaleDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.SaleDTO;

public class SaleAzureSqlDAO extends ConnectionSQL implements SaleDAO{
	
	private SaleAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SaleDAO build(Connection connection) {
		return new SaleAzureSqlDAO(connection);
	}

	@Override
	public void create(SaleDTO sale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(SaleDTO sale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int idSale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void find(SaleDTO idType) {
		// TODO Auto-generated method stub
		
	}

}
