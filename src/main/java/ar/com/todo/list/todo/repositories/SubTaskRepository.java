package ar.com.todo.list.todo.repositories;

import ar.com.todo.list.todo.repositories.entities.SubTaskEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTaskRepository extends CrudRepository<SubTaskEntity, String> {
    Iterable<SubTaskEntity> findAllByTaskId(String taskId);
}
