package co.movies.data.dao;

import java.util.List;

import co.movies.dto.SaleDTO;

public interface SaleDAO {
	
	void create(SaleDTO sale);

	void update(SaleDTO sale);

	void delete(int idSale);

	List<SaleDTO> find(SaleDTO sale);

}
