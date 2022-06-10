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
import co.movies.data.dao.SedeDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.CityDTO;
import co.movies.dto.SedeDTO;

public class SedeAzureSqlDAO extends ConnectionSQL implements SedeDAO {

	protected SedeAzureSqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public static SedeDAO build(Connection connection) {
		return new SedeAzureSqlDAO(connection);
	}

	@Override
	public void create(SedeDTO sede) {
		String sql = "INSERT INTO Campus (name, city, direction) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, sede.getName());
			preparedStatement.setInt(2, sede.getCity().getId());
			preparedStatement.setString(3, sede.getDirection());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to create the new sede on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to create the new sede on Azure SQL Server", exception);

		}

	}

	@Override
	public void update(SedeDTO sede) {
		String sql = "UPDATE product SET productName = ? WHERE productId=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, sede.getName());
			preparedStatement.setInt(2, sede.getCity().getId());
			preparedStatement.setString(3, sede.getDirection());
			preparedStatement.setInt(4, sede.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to update the sede on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to update the sede on Azure SQL Server", exception);

		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM product WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to delete the sede on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to delete the sede on Azure SQL Server", exception);
		}

	}

	@Override
	public List<SedeDTO> Find(SedeDTO sede) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<SedeDTO> results = new ArrayList<SedeDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, idNumber, price").append(SPACE);
		sb.append("From product ");

		if (!UtilObject.getUtilObject().isNull(sede)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sede.getId(), 0)) {
				sb.append("WHERE id = ?").append(SPACE);
				parameters.add(sede.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(sede.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(sede.getName()));
				setWhere = false;
			}

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sede.getCity().getId(), 0)) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("city = ? ");
				parameters.add(sede.getCity().getId());
				setWhere = false;
			}

			if (!UtilText.isEmpty(sede.getDirection())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("direction = ? ");
				parameters.add(UtilText.trim(sede.getDirection()));

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
					"There was a problem trying to retrive the sede on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to retrives the sede on Azure SQL Server", exception);

		}

		return results;
	}

	private List<SedeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<SedeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recovery the sede on Azure SQL Server",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to execute the query for recovery the sede on Azure SQL Server",
					exception);

		}

		return results;

	}

	private List<SedeDTO> assembleResults(ResultSet resultSet) {
		List<SedeDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (MoviesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to recover the sede on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to recover the sede on Azure SQL Server", exception);

		}

		return results;

	}

	private SedeDTO assembleDTO(ResultSet resultSet) {

		SedeDTO dto = new SedeDTO();

		try {
			CityDTO city = new CityDTO();
			city.setId(resultSet.getInt("city"));

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));
			dto.setCity(city);
			dto.setDirection(resultSet.getString("direction"));

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to assemble the sede on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to assemble the sede on Azure SQL Server", exception);

		}

		return dto;

	}

}
