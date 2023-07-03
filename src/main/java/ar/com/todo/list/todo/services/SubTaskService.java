package ar.com.todo.list.todo.services;

import ar.com.todo.list.todo.exceptions.SubTaskNotFoundException;
import ar.com.todo.list.todo.exceptions.TaskNotFoudException;
import ar.com.todo.list.todo.models.SubTask;

import java.util.List;

public interface SubTaskService {
    List<SubTask> getSubTasks(String taskId);

    SubTask getSubTask(String subTaskId) throws SubTaskNotFoundException;

    SubTask saveSubTask(String taskId, SubTask task) throws TaskNotFoudException;

    SubTask updateSubTask(SubTask task) throws SubTaskNotFoundException;
}
