package ar.com.todo.list.todo.services.impl;

import ar.com.todo.list.todo.enums.TaskState;
import ar.com.todo.list.todo.exceptions.BoardNotFoudException;
import ar.com.todo.list.todo.exceptions.TaskNotFoudException;
import ar.com.todo.list.todo.models.Board;
import ar.com.todo.list.todo.models.Task;
import ar.com.todo.list.todo.repositories.TaskRepository;
import ar.com.todo.list.todo.repositories.entities.BoardEntity;
import ar.com.todo.list.todo.repositories.entities.TaskEntity;
import ar.com.todo.list.todo.services.BoardService;
import ar.com.todo.list.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private BoardService boardService;

    @Override
    public List<Task> getTasks(String boardId) {
        return StreamSupport.stream(taskRepository.findAllByBoardId(boardId).spliterator(), false)
                .toList().stream().map(Task::from).collect(Collectors.toList());
    }

    @Override
    public Task getTask(String taskId) throws TaskNotFoudException {
        TaskEntity taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoudException(taskId));
        return Task.from(taskEntity);
    }

    @Override
    public Task saveTask(String boardId, Task task) throws BoardNotFoudException {
        Board board = boardService.getBoard(boardId);
        TaskEntity taskEntity = taskRepository.save(TaskEntity.from(board, task));
        return Task.from(taskEntity);
    }

    @Override
    public Task updateTask(String boardId, Task task) throws BoardNotFoudException, TaskNotFoudException {
        Board board = boardService.getBoard(boardId);
        TaskEntity taskEntity = taskRepository.findById(task.getId()).orElseThrow(() -> new TaskNotFoudException(task.getId()));
        taskEntity.setName(task.getName());
        taskEntity.setState(TaskState.getTaskState(task.getState()));
        taskEntity.setBoard(BoardEntity.from(board));
        return Task.from(taskRepository.save(taskEntity));
    }
}
