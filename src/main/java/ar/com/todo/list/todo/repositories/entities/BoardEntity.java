package ar.com.todo.list.todo.repositories.entities;

import ar.com.todo.list.todo.enums.BoardState;
import ar.com.todo.list.todo.models.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "board")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "board_id")
    private String id;
    @Column(name = "board_name")
    private String name;
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<TaskEntity> tasks;
    @Column(name = "board_state")
    private BoardState state;
    @Column(name = "board_created_at")
    private LocalDateTime createdAt;
    @Column(name = "board_last_update")
    private LocalDateTime lastUpdate;

    public static BoardEntity from(Board board) {
        return BoardEntity.builder()
                .id(board.getId())
                .name(board.getName())
                .tasks(board.getTasks() != null ? board.getTasks().stream().map(TaskEntity::from).collect(Collectors.toList()) : List.of())
                .state(BoardState.getBoardState(board.getState()))
                .build();
    }

    @PrePersist
    public void prePresist() {
        state = BoardState.BOARD_ACTIVE;
        createdAt = LocalDateTime.now();
        lastUpdate = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
