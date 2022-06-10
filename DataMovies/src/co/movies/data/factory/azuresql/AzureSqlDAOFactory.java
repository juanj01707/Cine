package co.movies.data.factory.azuresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import co.movies.crosscutting.util.sql.UtilConnection;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.crosscuttingmovies.exception.enumeration.ExceptionLocation;
import co.movies.data.dao.CityDAO;
import co.movies.data.dao.ClientDAO;
import co.movies.data.dao.IdTypeDAO;
import co.movies.data.dao.ProductDAO;
import co.movies.data.dao.SalaDAO;
import co.movies.data.dao.SaleDAO;
import co.movies.data.dao.SedeDAO;
import co.movies.data.dao.TicketDAO;
import co.movies.data.dao.azuresql.CityAzureSqlDAO;
import co.movies.data.dao.azuresql.ClientAzureSqlDAO;
import co.movies.data.dao.azuresql.IdTypeAzureSqlDAO;
import co.movies.data.dao.azuresql.ProductAzureSqlDAO;
import co.movies.data.dao.azuresql.SalaAzureSqlDAO;
import co.movies.data.dao.azuresql.SaleAzureSqlDAO;
import co.movies.data.dao.azuresql.SedeAzureSqlDAO;
import co.movies.data.dao.azuresql.TicketAzureSqlDAO;
import co.movies.data.factory.DAOFactory;

public class AzureSqlDAOFactory extends DAOFactory {

	private static final String UNEXPECTED_ERROR_TO_ROLLBACK_THE_TRANSACTION_WITH_SQL_SERVER = "An unexpected problem has ocurred trying to rollback the transaction with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_ROLLBACK_THE_TRANSACTION_WITH_SQL_SERVER = "There was a problem trying to rollback the transaction with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_ROLLBACK_THE_TRANSACTION_THE_DATABASE_IS_MANAGING_THE_TRANSACTION = "It's not possible to rollback the transaction because the database is managing the transaction";
	private static final String ERROR_TO_ROLLBACK_THE_TRANSACTION_THE_CONNECTION_IS_CLOSED = "It's not possible to rollback the transaction because the connection is closed";
	private static final String UNEXPECTED_ERROR_TO_COMMIT_THE_TRANSACTION_WITH_SQL_SERVER = "An unexpected problem has ocurred trying to commit the transaction with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_COMMIT_THE_TRANSACTION_WITH_SQL_SERVER = "There was a problem trying to commit the transaction with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_COMMIT_THE_TRANSACTION_THE_DATABASE_IS_MANAGING_THE_TRANSACTION = "It's not possible to commit the transaction because the database is managing the transaction";
	private static final String ERROR_TO_COMMIT_THE_TRANSACTION_THE_CONNECTION_IS_CLOSED = "It's not possible to commit the transaction because the connection is closed";
	private static final String UNEXPECTED_ERROR_TO_INIT_THE_TRANSACTION_WITH_SQL_SERVER = "An unexpected problem has ocurred trying to init the transaction with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_INIT_THE_TRANSACTION_WITH_SQL_SERVER = "There was a problem trying to init the transaction with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_INIT_THE_TRANSACTION_IT_WAS_ALREADY_INITIATED = "It's not possible to init the transaction because it was already initiated";
	private static final String ERROR_TO_INIT_THE_TRANSACTION_THE_CONNECTION_IS_CLOSED = "It's not possible to init the transaction because the connection is closed";
	private static final String UNEXPECTED_ERROR_TO_CLOSE_THE_CONNECTION = "An unexpected problem has ocurred trying to close the connection with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_CLOSE_THE_CONNECTION = "There was a problem trying to close the connection with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROR_TO_CLOSE_THE_CONNECTION_BECAUSE_CLOSED = "It's not possible close a connection because its already is closed";
	private static final String UNEXPECTED_ERROR_GET_THE_CONNECTION_SQL_SERVER = "An unexpected problem has ocurred trying to get the connection with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	private static final String ERROT_GET_THE_CONNECTION_WITH_SQL_SERVER = "There was a problem trying to get the connection with sql server at jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser";
	
	
	private Connection connection;

	private AzureSqlDAOFactory() {
		openConnection();
	}

	public static DAOFactory create() {
		return new AzureSqlDAOFactory();
	}

	@Override
	protected void openConnection() {
		String stringConnection = "jdbc:sqlserver://movies-csp-database-server.database.windows.net:1433;database=movies-db;user=academicDmlUser;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
		try {
			connection = DriverManager.getConnection(stringConnection);
		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalException(ERROT_GET_THE_CONNECTION_WITH_SQL_SERVER, exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalException(UNEXPECTED_ERROR_GET_THE_CONNECTION_SQL_SERVER, exception,
					ExceptionLocation.DATA);
		}

	}

	@Override
	protected Connection getConnection() {
		return connection;
	}

	@Override
	public void closeConnection() {
		if (UtilConnection.isClosed(getConnection())) {
			throw MoviesException.buildTechnicalException(ERROR_TO_CLOSE_THE_CONNECTION_BECAUSE_CLOSED);
		}

		try {
			getConnection().close();
		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalException(ERROR_TO_CLOSE_THE_CONNECTION, exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalException(UNEXPECTED_ERROR_TO_CLOSE_THE_CONNECTION, exception,
					ExceptionLocation.DATA);
		}

	}

	@Override
	public void initTransaction() {
		if (UtilConnection.isClosed(getConnection())) {
			throw MoviesException.buildTechnicalException(ERROR_TO_INIT_THE_TRANSACTION_THE_CONNECTION_IS_CLOSED);
		}

		try {

			if (!getConnection().getAutoCommit()) {
				throw MoviesException.buildTechnicalException(ERROR_TO_INIT_THE_TRANSACTION_IT_WAS_ALREADY_INITIATED);
			}

			getConnection().setAutoCommit(false);
		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalException(ERROR_TO_INIT_THE_TRANSACTION_WITH_SQL_SERVER, exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalException(UNEXPECTED_ERROR_TO_INIT_THE_TRANSACTION_WITH_SQL_SERVER,
					exception, ExceptionLocation.DATA);
		}

	}

	@Override
	public void commitTransaction() {
		if (UtilConnection.isClosed(getConnection())) {
			throw MoviesException.buildTechnicalException(ERROR_TO_COMMIT_THE_TRANSACTION_THE_CONNECTION_IS_CLOSED);
		}

		try {
			if (getConnection().getAutoCommit()) {
				throw MoviesException.buildTechnicalException(
						ERROR_TO_COMMIT_THE_TRANSACTION_THE_DATABASE_IS_MANAGING_THE_TRANSACTION);
			}

			getConnection().commit();
		} catch (MoviesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalException(ERROR_TO_COMMIT_THE_TRANSACTION_WITH_SQL_SERVER, exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalException(UNEXPECTED_ERROR_TO_COMMIT_THE_TRANSACTION_WITH_SQL_SERVER,
					exception, ExceptionLocation.DATA);
		}

	}

	@Override
	public void rollbackTransaction() {
		if (UtilConnection.isClosed(getConnection())) {
			throw MoviesException.buildTechnicalException(ERROR_TO_ROLLBACK_THE_TRANSACTION_THE_CONNECTION_IS_CLOSED);
		}

		try {

			if (getConnection().getAutoCommit()) {
				throw MoviesException.buildTechnicalException(
						ERROR_TO_ROLLBACK_THE_TRANSACTION_THE_DATABASE_IS_MANAGING_THE_TRANSACTION);
			}

			getConnection().rollback();
		} catch (MoviesException exception) {
			throw exception;
		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalException(ERROR_TO_ROLLBACK_THE_TRANSACTION_WITH_SQL_SERVER, exception,
					ExceptionLocation.DATA);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalException(UNEXPECTED_ERROR_TO_ROLLBACK_THE_TRANSACTION_WITH_SQL_SERVER,
					exception, ExceptionLocation.DATA);
		}

	}

	@Override
	public SaleDAO getSaleDAO() {
		return SaleAzureSqlDAO.build(getConnection());
	}

	@Override
	public CityDAO getCityDAO() {
		return CityAzureSqlDAO.build(getConnection());
	}

	@Override
	public ClientDAO getClientDAO() {
		return ClientAzureSqlDAO.build(getConnection());
	}

	@Override
	public IdTypeDAO getIdType() {
		return IdTypeAzureSqlDAO.build(getConnection());
	}

	@Override
	public ProductDAO getProductDAO() {
		return ProductAzureSqlDAO.build(getConnection());
	}

	@Override
	public SalaDAO getSalaDAO() {
		return SalaAzureSqlDAO.build(getConnection());
	}

	@Override
	public SedeDAO getSedeDAO() {
		return SedeAzureSqlDAO.build(getConnection());
	}

	

	@Override
	public TicketDAO getTicketDAO() {
		return TicketAzureSqlDAO.build(getConnection());
	}

}
