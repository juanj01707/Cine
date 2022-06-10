package co.movies.data.dao;

import java.util.List;

import co.movies.dto.TicketDTO;

public interface TicketDAO {
	void create(TicketDTO ticket);
	void update(TicketDTO ticket);
	void delete(int ticketId);
	List<TicketDTO> Find(TicketDTO ticket);

}
