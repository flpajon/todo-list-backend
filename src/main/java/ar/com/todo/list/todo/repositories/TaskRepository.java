package ar.com.todo.list.todo.repositories;

import ar.com.todo.list.todo.repositories.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, String> {
    Iterable<TaskEntity> findAllByBoardId(String boardId);
}
