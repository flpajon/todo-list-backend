package ar.com.todo.list.todo.controllers;

import ar.com.todo.list.todo.controllers.response.GenericResponse;
import ar.com.todo.list.todo.models.Task;
import ar.com.todo.list.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<GenericResponse<List<Task>>> getBoardTasks(@RequestParam("board_id") String boardId) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(taskService.getTasks(boardId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{task_id}")
    public ResponseEntity<GenericResponse<Task>> getTask(@PathVariable("task_id") String taskId) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(taskService.getTask(taskId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Task>> saveTask(@RequestParam("board_id") String boardId, @RequestBody Task task) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(taskService.saveTask(boardId, task)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<GenericResponse<Task>> updateTask(@RequestParam("board_id") String boardId, @RequestBody Task task) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(taskService.updateTask(boardId, task)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
