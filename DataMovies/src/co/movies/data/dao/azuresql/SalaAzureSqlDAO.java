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
import co.movies.data.dao.SalaDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.ChairDTO;
import co.movies.dto.SalaDTO;
import co.movies.dto.SalaStatementDTO;
import co.movies.dto.SedeDTO;

public class SalaAzureSqlDAO extends ConnectionSQL implements SalaDAO {

	protected SalaAzureSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static SalaDAO build(Connection connection) {
		return new SalaAzureSqlDAO(connection);
	}

	@Override
	public void create(SalaDTO sala) {
		String sql = "INSERT INTO sala (salaName, salaId, ) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, sala.getSalaName());
			preparedStatement.setInt(2, sala.getSalaId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to create the new sala on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to create the new sala on Azure SQL Server",
					exception);

		}

	}

	@Override
	public void update(SalaDTO sala) {
		String sql = "UPDATE sale SET saleName = ? WHERE salaId=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, sala.getSalaName());
			preparedStatement.setInt(2, sala.getSalaId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to update the sala on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to update the sala on Azure SQL Server", exception);

		}
	}

	@Override
	public void delete(int salaId) {
		String sql = "DELETE FROM sala WHERE id = ?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setInt(1, salaId);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to delete the sala on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to delete the sala on Azure SQL Server", exception);
		}

	}

	@Override
	public List<SalaDTO> find(SalaDTO sala) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<SalaDTO> results = new ArrayList<SalaDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, sede, salaStatement").append(SPACE);
		sb.append("From product ");

		if (!UtilObject.getUtilObject().isNull(sala)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sala.getSalaId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(sala.getSalaId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(sala.getSalaName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(sala.getSalaName()));

			}

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sala.getSede().getId(),0)) {
				sb.append("WHERE sede = ?  ");
				parameters.add(sala.getSede().getId());
				setWhere = false;

			}

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sala.getSalaStatement().getId(), 0)) {
				sb.append("WHERE salaStatement = ? ");
				parameters.add(sala.getSalaStatement().getId());
				setWhere = false;

			}
			
			if (UtilNumeric.getUtilNumeric().isGreaterThan(sala.getChair().getId(), 0)) {
				sb.append("WHERE chair = ? ");
				parameters.add(sala.getChair().getId());
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
					"There was a problem trying to retrive the sala on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to retrives the sala on Azure SQL Server", exception);

		}

		return results;

	}

	private List<SalaDTO> executeQuery(PreparedStatement preparedStatement) {

		List<SalaDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recovery the sala on Azure SQL Server",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to execute the query for recovery the sala on Azure SQL Server",
					exception);

		}

		return results;

	}

	private List<SalaDTO> assembleResults(ResultSet resultSet) {
		List<SalaDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (MoviesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to recover the sala on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to recover the sala on Azure SQL Server", exception);

		}

		return results;

	}

	private SalaDTO assembleDTO(ResultSet resultSet) {

		SalaDTO dto = new SalaDTO();

		try {

			SedeDTO sede = new SedeDTO();
			sede.setId(resultSet.getInt("sede"));

			SalaStatementDTO salaStatement = new SalaStatementDTO();
			salaStatement.setId(resultSet.getInt("sede"));

			ChairDTO chair = new ChairDTO();
			chair.setId(resultSet.getInt("sede"));

			dto.setSalaId(resultSet.getInt("id"));
			dto.setSalaName(resultSet.getString("name"));
			dto.setSede(sede);
			dto.setSalaStatement(salaStatement);
			dto.setChair(chair);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to assemble the sala on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to assemble the sala on Azure SQL Server", exception);

		}

		return dto;

	}

}
