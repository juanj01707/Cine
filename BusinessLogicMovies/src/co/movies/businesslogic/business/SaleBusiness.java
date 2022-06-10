package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.SaleDTO;

public interface SaleBusiness {
	void create(SaleDTO dto);
	
	void update (SaleDTO dto);
	
	void delete (int id);
	
	List<SaleDTO> find(SaleDTO dto);




}
