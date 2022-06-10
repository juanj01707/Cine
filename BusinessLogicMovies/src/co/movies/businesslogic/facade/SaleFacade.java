package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.SaleDTO;

public interface SaleFacade {
	void create(SaleDTO dto);
	
	void update (SaleDTO dto);
	
	void delete (int id);
	
	List<SaleDTO> find(SaleDTO dto);




}
