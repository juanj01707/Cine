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
import co.movies.data.dao.SaleDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.SaleDTO;

public class SaleAzureSqlDAO extends ConnectionSQL implements SaleDAO {

	private SaleAzureSqlDAO(Connection connection) {
		super(connection);
	}

	public static SaleDAO build(Connection connection) {
		return new SaleAzureSqlDAO(connection);
	}

	@Override
	public void create(SaleDTO sale) {
		String sql = "INSERT INTO sale (IdSale, saleIdNumber, value ) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, sale.getIdSale());
			preparedStatement.setString(2, sale.getSaleIdNumber());
			preparedStatement.setInt(3, sale.getValue());
			
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to create the new sale on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to create the new sale on Azure SQL Server",
					exception);

		}
	}

	@Override
	public void update(SaleDTO sale) {
		String sql = "UPDATE sale WHERE productId =?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, sale.getIdSale());
			preparedStatement.setString(2, sale.getSaleIdNumber());
			preparedStatement.setInt(3, sale.getValue());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to update the sale on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to update the sale on Azure SQL Server", exception);

		}

	}

	@Override
	public void delete(int idSale) {
		String sql = "DELETE FROM sale WHERE id = ?";
		
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {

			preparedStatement.setInt(1, idSale);

			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to delete the sale on Azure SQL Server", exception);
		} catch (Exception exception) {
			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to delete the sale on Azure SQL Server", exception);
		}

	}

	@Override
	public List<SaleDTO> find(SaleDTO sale) {
		boolean setWhere = true;
		List<SaleDTO> parameters = new ArrayList<>();
		List<SaleDTO> results = new ArrayList<SaleDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, idNumber, value").append(SPACE);
		sb.append("From sale ");

		if (!UtilObject.getUtilObject().isNull(sale)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sale.getIdSale(), 0)) {
				sb.append("WHERE id = ? ");
				// parameters.add(sale.getIdSale());
				setWhere = false;

			}

			if (!UtilText.isEmpty(sale.getSaleIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				// parameters.add(UtilText.trim(sale.getSaleIdNumber()));

			}

			if (UtilNumeric.getUtilNumeric().isGreaterThan(sale.getValue(), 0)) {
				sb.append("WHERE value = ?");
				// parameters.add(sale.getValue());
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
					"There was a problem trying to retrive the sale on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to retrives the sale on Azure SQL Server", exception);

		}

		return results;

	}

	private List<SaleDTO> executeQuery(PreparedStatement preparedStatement) {

		List<SaleDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recovery the sale on Azure SQL Server",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to execute the query for recovery the sale on Azure SQL Server",
					exception);

		}

		return results;

	}

	private List<SaleDTO> assembleResults(ResultSet resultSet) {
		List<SaleDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (MoviesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to recover the sale on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to recover the sale on Azure SQL Server", exception);

		}

		return results;

	}

	private SaleDTO assembleDTO(ResultSet resultSet) {

		SaleDTO dto = new SaleDTO();

		try {

			dto.setIdSale(resultSet.getInt("id"));
			dto.setSaleIdNumber(resultSet.getString("idNumber"));
			dto.setValue(resultSet.getInt("price"));

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to assemble the sale on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to assemble the sale on Azure SQL Server", exception);

		}

		return dto;

	}

}
