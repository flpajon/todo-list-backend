package ar.com.todo.list.todo.repositories;

import ar.com.todo.list.todo.repositories.entities.BoardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends CrudRepository<BoardEntity, String> {
}
