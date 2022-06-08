package co.movies.data.dao.connection;

import java.sql.Connection;

import co.movies.crosscutting.util.sql.UtilConnection;
import co.movies.crosscuttingmovies.exception.MoviesException;

public class ConnectionSQL {
	private static final String ERROR_CREATING_DAO = "It's not possible to create the specific DAO because connection is closed";
	private Connection connection;

	protected ConnectionSQL(Connection connection) {
		if (UtilConnection.isClosed(connection)) {
			throw MoviesException.buildTechnicalDataException(ERROR_CREATING_DAO);
		}

		setConnection(connection);
	}

	protected Connection getConnection() {
		return connection;
	}

	private void setConnection(Connection connection) {
		this.connection = connection;
	}

}
