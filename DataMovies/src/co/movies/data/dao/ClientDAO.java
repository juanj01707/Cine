package co.movies.data.dao;

import java.util.List;

import co.movies.dto.ClientDTO;

public interface ClientDAO {

	void create(ClientDTO client);

	void update(ClientDTO client);

	void delete(int id);

	List<ClientDTO> find(ClientDTO client);
}
