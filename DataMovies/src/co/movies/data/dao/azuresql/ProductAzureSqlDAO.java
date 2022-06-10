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
import co.movies.data.dao.ProductDAO;
import co.movies.data.dao.connection.ConnectionSQL;
import co.movies.dto.ProductDTO;

public class ProductAzureSqlDAO extends ConnectionSQL implements ProductDAO {

	protected ProductAzureSqlDAO(Connection connection) {
		super(connection);

	}

	public static ProductDAO build(Connection connection) {
		return new ProductAzureSqlDAO(connection);
	}

	@Override
	public void create(ProductDTO product) {
		String sql = "INSERT INTO Product (productName, productId, productIdNumber, price) VALUES(?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getProductId());
			preparedStatement.setString(3, product.getProductIdNumber());
			preparedStatement.setInt(4, product.getPrice());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to create the new product on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to create the new product on Azure SQL Server",
					exception);

		}

	}

	@Override
	public void update(ProductDTO product) {
		String sql = "UPDATE product SET productName = ? WHERE productId=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, product.getProductName());
			preparedStatement.setInt(2, product.getProductId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to update the product on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to update the product on Azure SQL Server", exception);

		}

	}

	@Override
	public void delete(int productId) {
			String sql = "DELETE FROM product WHERE id = ?";
	
			try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
	
				preparedStatement.setInt(1, productId);
	
				preparedStatement.executeUpdate();
	
			} catch (SQLException exception) {
				throw MoviesException.buildTechnicalDataException(
						"There was a problem trying to delete the product on Azure SQL Server", exception);
			} catch (Exception exception) {
				throw MoviesException.buildTechnicalDataException(
						"An unexpected problem has ocurred trying to delete the product on Azure SQL Server", exception);
			}

	}

	@Override
	public List<ProductDTO> find(ProductDTO product) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<ProductDTO> results = new ArrayList<ProductDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name, idNumber, price").append(SPACE);
		sb.append("From product ");

		if (!UtilObject.getUtilObject().isNull(product)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(product.getProductId(), 0)) {
				sb.append("WHERE id = ? ");
				parameters.add(product.getProductId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(product.getProductName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(product.getProductName()));

			}

			if (!UtilText.isEmpty(product.getProductIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idNumber = ? ");
				parameters.add(UtilText.trim(product.getProductIdNumber()));

			}

			if (UtilNumeric.getUtilNumeric().isGreaterThan(product.getPrice(), 0)) {
				sb.append("WHERE price = ? ");
				parameters.add(product.getPrice());
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
					"There was a problem trying to retrive the product on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to retrives the product on Azure SQL Server", exception);

		}

		return results;
	}

	private List<ProductDTO> executeQuery(PreparedStatement preparedStatement) {

		List<ProductDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recovery the products on Azure SQL Server",
					exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to execute the query for recovery the product on Azure SQL Server",
					exception);

		}

		return results;

	}

	private List<ProductDTO> assembleResults(ResultSet resultSet) {
		List<ProductDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (MoviesException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to recover the product on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to recover the product on Azure SQL Server", exception);

		}

		return results;

	}

	private ProductDTO assembleDTO(ResultSet resultSet) {

		ProductDTO dto = new ProductDTO();

		try {

			dto.setProductId(resultSet.getInt("id"));
			dto.setProductName(resultSet.getString("name"));
			dto.setProductIdNumber(resultSet.getString("idNumber"));
			dto.setPrice(resultSet.getInt("price"));

		} catch (SQLException exception) {

			throw MoviesException.buildTechnicalDataException(
					"There was a problem trying to assemble the product on Azure SQL Server", exception);

		} catch (Exception exception) {

			throw MoviesException.buildTechnicalDataException(
					"An unexpected problem has ocurred trying to assemble the product on Azure SQL Server", exception);

		}

		return dto;

	}

}
