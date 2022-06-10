package co.movies.data.dao.azuresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static co.movies.crosscutting.util.text.UtilText.SPACE;

import co.movies.crosscutting.util.numeric.UtilNumeric;
import co.movies.crosscutting.util.object.UtilObject;
import co.movies.crosscutting.util.text.UtilText;
import co.movies.crosscuttingmovies.exception.MoviesException;
import co.movies.data.dao.ClientDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.ClientDTO;
import co.movies.dto.IdTypeDTO;



public class ClientAzureSqlDAO extends ConnectionSQL implements ClientDAO {

	protected ClientAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static ClientDAO build(Connection connection) {
		return new ClientAzureSqlDAO(connection);
	}

	@Override
	public void create(ClientDTO client) {
		String sql = "INSERT INTO Client (name, idType, idNumber) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, client.getName());
			preparedStatement.setInt(2, client.getIdType().getId());
			preparedStatement.setString(3, client.getIdNumber());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to create a new client registry on sql server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new client registry on sql server", exception);

		}		
	}

	@Override
	public void update(ClientDTO client) {
		String sql = "UPDATE Client SET name = ?, idType=?, idNumber=?  WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, client.getName());
			preparedStatement.setInt(2, client.getIdType().getId());
			preparedStatement.setString(3, client.getIdNumber());
			preparedStatement.setInt(4, client.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to update a new Client registry on sql server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to update Client registry on sql server", exception);

		}	
		
	}

	@Override
	public void delete(int id) {

		String sql = "DELETE FROM Client WHERE (id=?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1,id);
			
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to delete a Client registry on sql server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a Client registry on sql server", exception);

		
		}
	}

	@Override
	public List<ClientDTO> find(ClientDTO client) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<ClientDTO> results = new ArrayList<ClientDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, idNumber, idType").append(SPACE);
		sb.append("From Client ");

		if (!UtilObject.getUtilObject().isNull(client)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(client.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(client.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(client.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(client.getName()));
				setWhere = false;
			}
			
			if (!UtilText.isEmpty(client.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(client.getIdNumber()));
				setWhere = false;
			}			
			
			if (UtilNumeric.getUtilNumeric().isGreaterThan(client.getIdType().getId(),0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idType = ? ");
				parameters.add(client.getIdType().getId());
				
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
					"There was a problem trying to find Cliente registry on sql server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an Client registry on sql server", exception);

		}

		return results;
		
	}
	
	private List<ClientDTO> executeQuery(PreparedStatement preparedStatement) {

		List<ClientDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover client registry on sql server",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover client registry on sql server",
					exception);

		}

		return results;

	}

	private List<ClientDTO> assembleResults(ResultSet resultSet) {
		List<ClientDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (MoviesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException("There was a problem trying to recover the client",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the clients registry on sql server", exception);

		}

		return results;

	}

	private ClientDTO assembleDTO(ResultSet resultSet) {

		ClientDTO dto = new ClientDTO();

		try {
			
			IdTypeDTO idType = new IdTypeDTO(resultSet.getInt("idType"), "");

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setIdNumber(resultSet.getString("idNumber"));
			dto.setIdType(idType);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException("There was a problem trying to assemble the clientss",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id clients on sql server", exception);

		}

		return dto;

	}

}
