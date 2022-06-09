package co.movies.data.dao;

import co.movies.dto.ClientDTO;

public interface ClientDAO {

	void create(ClientDTO student);

	void update(ClientDTO student);

	void delete(int id);

	void find(ClientDTO student);
}
