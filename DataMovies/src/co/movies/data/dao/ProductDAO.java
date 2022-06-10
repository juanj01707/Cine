package co.movies.data.dao;

import java.util.List;

import co.movies.dto.ProductDTO;

public interface ProductDAO {
	void create(ProductDTO product);

	void update(ProductDTO product);

	void delete(int productId);

	List<ProductDTO> find(ProductDTO product);

}
