package co.movies.data.dao;

import co.movies.dto.SaleDTO;

public interface SaleDAO {
	
	void create(SaleDTO sale);

	void update(SaleDTO sale);

	void delete(int idSale);

	void find(SaleDTO idType);

}
