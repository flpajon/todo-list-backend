package ar.com.todo.list.todo.services;

import ar.com.todo.list.todo.exceptions.BoardNotFoudException;
import ar.com.todo.list.todo.exceptions.TaskNotFoudException;
import ar.com.todo.list.todo.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasks(String boardId);

    Task getTask(String taskId) throws TaskNotFoudException;

    Task saveTask(String boardId, Task task) throws BoardNotFoudException;

    Task updateTask(String boardId, Task task) throws BoardNotFoudException, TaskNotFoudException;
}
