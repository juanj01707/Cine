package co.movies.businesslogic.business;

import java.util.List;

import co.movies.dto.TicketDTO;

public interface TicketBusiness {
	void create(TicketDTO dto);

	void update(TicketDTO dto);

	void delete(int id);

	List<TicketDTO> find(TicketDTO dto);

}
