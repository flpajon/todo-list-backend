package ar.com.todo.list.todo.models;

import ar.com.todo.list.todo.repositories.entities.BoardEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class Board {
    private String id;
    private String name;
    private List<Task> tasks;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdate;

    public static Board from(BoardEntity boardEntity) {
        return Board.builder()
                .id(boardEntity.getId())
                .name(boardEntity.getName())
                .tasks(boardEntity.getTasks().stream().map(Task::from).collect(Collectors.toList()))
                .state(boardEntity.getState().name())
                .createdAt(boardEntity.getCreatedAt())
                .lastUpdate(boardEntity.getLastUpdate())
                .build();
    }
}
