package ar.com.todo.list.todo.controllers;

import ar.com.todo.list.todo.controllers.response.GenericResponse;
import ar.com.todo.list.todo.models.SubTask;
import ar.com.todo.list.todo.services.SubTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/subtasks")
public class SubTaskController {

    @Autowired
    private SubTaskService subTaskService;

    @GetMapping()
    public ResponseEntity<GenericResponse<List<SubTask>>> getTaskSubTasks(@RequestParam("task_id") String taskId) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(subTaskService.getSubTasks(taskId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{subtask_id}")
    public ResponseEntity<GenericResponse<SubTask>> getSubTask(@PathVariable("subtask_id") String subTaskId) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(subTaskService.getSubTask(subTaskId)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<GenericResponse<SubTask>> saveSubTask(@RequestParam("task_id") String taskId, @RequestBody SubTask subTask) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(subTaskService.saveSubTask(taskId, subTask)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<GenericResponse<SubTask>> updateSubTask(@RequestBody SubTask subTask) {
        try {
            return new ResponseEntity<>(GenericResponse.Success(subTaskService.updateSubTask(subTask)), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(GenericResponse.Error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
