package ar.com.todo.list.todo.models;

import ar.com.todo.list.todo.repositories.entities.SubTaskEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SubTask {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    public static SubTask from(SubTaskEntity subTaskEntity) {
        return SubTask.builder()
                .id(subTaskEntity.getId())
                .name(subTaskEntity.getName())
                .createdAt(subTaskEntity.getCreatedAt())
                .lastUpdate(subTaskEntity.getLastUpdate())
                .build();
    }

}
