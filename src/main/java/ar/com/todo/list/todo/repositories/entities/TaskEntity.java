package ar.com.todo.list.todo.repositories.entities;

import ar.com.todo.list.todo.enums.TaskState;
import ar.com.todo.list.todo.models.Board;
import ar.com.todo.list.todo.models.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "task_id")
    private String id;
    @Column(name = "task_name")
    private String name;
    @OneToMany(mappedBy = "task")
    private List<SubTaskEntity> subTasks;
    @Column(name = "task_state")
    private TaskState state;
    @Column(name = "task_created_at")
    private LocalDateTime createdAt;
    @Column(name = "task_last_update")
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private BoardEntity board;

    public static TaskEntity from(Task task) {
        return TaskEntity.builder()
                .id(task.getId())
                .name(task.getName())
                .subTasks(task.getSubTasks() != null ? task.getSubTasks().stream().map(SubTaskEntity::from).collect(Collectors.toList()) : List.of())
                .state(TaskState.getTaskState(task.getState()))
                .build();
    }

    public static TaskEntity from(Board board, Task task) {
        return TaskEntity.builder()
                .id(task.getId())
                .name(task.getName())
                .subTasks(task.getSubTasks() != null ? task.getSubTasks().stream().map(SubTaskEntity::from).collect(Collectors.toList()) : List.of())
                .state(TaskState.getTaskState(task.getState()))
                .board(BoardEntity.from(board))
                .build();
    }

    @PrePersist
    public void prePresist() {
        state = TaskState.TASK_CREATED;
        createdAt = LocalDateTime.now();
        lastUpdate = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
