package ar.com.todo.list.todo.services.impl;

import ar.com.todo.list.todo.exceptions.SubTaskNotFoundException;
import ar.com.todo.list.todo.exceptions.TaskNotFoudException;
import ar.com.todo.list.todo.models.SubTask;
import ar.com.todo.list.todo.models.Task;
import ar.com.todo.list.todo.repositories.SubTaskRepository;
import ar.com.todo.list.todo.repositories.entities.SubTaskEntity;
import ar.com.todo.list.todo.services.SubTaskService;
import ar.com.todo.list.todo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubTaskServiceImpl implements SubTaskService {

    @Autowired
    private SubTaskRepository subTaskRepository;
    @Autowired
    private TaskService taskService;

    @Override
    public List<SubTask> getSubTasks(String taskId) {
        return StreamSupport.stream(subTaskRepository.findAllByTaskId(taskId).spliterator(), false)
                .toList().stream().map(SubTask::from).collect(Collectors.toList());
    }

    @Override
    public SubTask getSubTask(String subTaskId) throws SubTaskNotFoundException {
        SubTaskEntity subTaskEntity = subTaskRepository.findById(subTaskId).orElseThrow(() -> new SubTaskNotFoundException(subTaskId));
        return SubTask.from(subTaskEntity);
    }

    @Override
    public SubTask saveSubTask(String taskId, SubTask subTask) throws TaskNotFoudException {
        Task task = taskService.getTask(taskId);
        SubTaskEntity subTaskEntity = subTaskRepository.save(SubTaskEntity.from(task, subTask));
        return SubTask.from(subTaskEntity);
    }

    @Override
    public SubTask updateSubTask(SubTask task) throws SubTaskNotFoundException {
        SubTaskEntity subTaskEntity = subTaskRepository.findById(task.getId()).orElseThrow(() -> new SubTaskNotFoundException(task.getId()));
        subTaskEntity.setName(task.getName());
        return SubTask.from(subTaskRepository.save(subTaskEntity));
    }
}
