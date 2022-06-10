package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.ProductDTO;

public interface ProductFacade {
	
void create(ProductDTO dto);
	
	void update (ProductDTO dto);
	
	void delete (int id);
	
	List<ProductDTO> find(ProductDTO dto);
	


}
