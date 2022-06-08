package co.movies.crosscutting.util.sql;

import java.sql.Connection;
import java.sql.SQLException;

import co.movies.crosscutting.exception.GeneralException;
import co.movies.crosscutting.util.object.UtilObject;

public class UtilConnection {
	
	private static final String VALIDATE_CONNECTION_ERROR = "Problems trying to validate if connection was closed!";
	private static final String CONNECTION_ERROR = "Connection is null!!!";

	private UtilConnection() {
	}

	public static boolean isClosed(Connection connection) {

		if (UtilObject.getUtilObject().isNull(connection)) {
			throw GeneralException.build(CONNECTION_ERROR);
		}

		try {
			return connection.isClosed();
		} catch (SQLException exception) {
			throw GeneralException.build(VALIDATE_CONNECTION_ERROR, exception);
		}
	}

	public static boolean isOpen(Connection connection) {
		return !isClosed(connection);
	}

}
