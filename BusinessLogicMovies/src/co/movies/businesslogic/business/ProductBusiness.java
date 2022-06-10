package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.ProductDTO;

public interface ProductBusiness {
	
void create(ProductDTO dto);
	
	void update (ProductDTO dto);
	
	void delete (int id);
	
	List<ProductDTO> find(ProductDTO dto);
	


}
