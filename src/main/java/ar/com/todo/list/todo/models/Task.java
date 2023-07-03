package ar.com.todo.list.todo.models;

import ar.com.todo.list.todo.repositories.entities.TaskEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Task {
    private String id;
    private String name;
    private List<SubTask> subTasks;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    public static Task from(TaskEntity taskEntity) {
        return Task.builder()
                .id(taskEntity.getId())
                .name(taskEntity.getName())
                .subTasks(taskEntity.getSubTasks().stream().map(SubTask::from).collect(Collectors.toList()))
                .state(taskEntity.getState().name())
                .createdAt(taskEntity.getCreatedAt())
                .lastUpdate(taskEntity.getLastUpdate())
                .build();
    }
}
