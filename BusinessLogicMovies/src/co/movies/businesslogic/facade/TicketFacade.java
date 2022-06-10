package co.movies.businesslogic.facade;

import java.util.List;

import co.movies.dto.TicketDTO;

public interface TicketFacade {
	void create(TicketDTO dto);

	void update(TicketDTO dto);

	void delete(int id);

	List<TicketDTO> find(TicketDTO dto);

}
