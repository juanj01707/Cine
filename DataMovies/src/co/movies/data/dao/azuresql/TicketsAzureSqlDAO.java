package co.movies.data.dao.azuresql;

import static co.movies.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.movies.crosscutting.util.numeric.UtilNumeric;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.data.dao.TicketDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.ProductDTO;
import co.movies.dto.TicketDTO;

public class TicketsAzureSqlDAO extends ConnectionSQL implements TicketDAO{

	protected TicketsAzureSqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public static TicketDAO build(Connection connection) {
		return new TicketsAzureSqlDAO(connection);
	}

	@Override
	public void create(TicketDTO ticket) {
		String sql = "INSERT INTO Ticket (ticketId, ticketIdNumber, funtion, price) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, ticket.getTicketId());
			preparedStatement.setString(2, ticket.getTicketIdNumber());
			//preparedStatement.setInt(3, ticket.getFuntion().getId());
			preparedStatement.setInt(4, ticket.getPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to create the new Ticket on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to create the new Ticket on Azure SQL Server",
					exception);

		}
	}

	@Override
	public void update(TicketDTO ticket) {
		String sql = "UPDATE Ticket SET TicketId = ? WHERE ticketIdNumber = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, ticket.getTicketId());
			preparedStatement.setString(2, ticket.getTicketIdNumber());
			//preparedStatement.setInt(3, ticket.getFuntion().getId());
			preparedStatement.setInt(4, ticket.getPrice());
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to update the product on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to update the product on Azure SQL Server", exception);

		}
	}

	@Override
	public void delete(int ticketId) {
		String sql = "DELETE FROM Ticket WHERE id = ?";
		
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setInt(1, ticketId);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to delete the Ticket on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to delete the Ticket on Azure SQL Server", exception);
		}
	}

	@Override
	public List<TicketDTO> Find(TicketDTO ticket) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<TicketDTO> results = new ArrayList<TicketDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, idNumber, funcion, price").append(SPACE);
		sb.append("From product ");

		if (!UtilObject.getUtilObject().isNull(ticket)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(ticket.getTicketId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(ticket.getTicketId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(ticket.getTicketIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(ticket.getTicketIdNumber()));

			}

			if (UtilNumeric.getUtilNumeric().isGreaterThan(ticket.getPrice(), 0)) {
				sb.append("WHERE price = ? ");
				parameters.add(ticket.getPrice());
				setWhere = false;

			}

		}

		sb.append("ORDER BY name ASC");

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		} catch (MoviesException exception) {
			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to retrive the Ticket on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to retrives the Ticket on Azure SQL Server", exception);

		}

		return results;
	}
	
	private List<TicketDTO> executeQuery(PreparedStatement preparedStatement) {

		List<TicketDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recovery the Ticket on Azure SQL Server",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to execute the query for recovery the Ticket on Azure SQL Server",
					exception);

		}

		return results;

	}

	private List<TicketDTO> assembleResults(ResultSet resultSet) {
		List<TicketDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (MoviesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to recover the Ticket on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to recover the Ticket on Azure SQL Server", exception);

		}

		return results;

	}

	private TicketDTO assembleDTO(ResultSet resultSet) {

		TicketDTO dto = new TicketDTO();

		try {

			dto.setTicketId(resultSet.getInt("id"));
			dto.setTicketIdNumber(resultSet.getString("idNumber"));
			dto.setPrice(resultSet.getInt("price"));

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to assemble the Ticket on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to assemble the Ticket on Azure SQL Server", exception);

		}

		return dto;

	}

}
