package ar.com.todo.list.todo.repositories.entities;

import ar.com.todo.list.todo.models.SubTask;
import ar.com.todo.list.todo.models.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "subtask")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "sub_task_id")
    private String id;
    @Column(name = "sub_task_name")
    private String name;
    @Column(name = "sub_task_created_at")
    private LocalDateTime createdAt;
    @Column(name = "sub_task_last_update")
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private TaskEntity task;

    public static SubTaskEntity from(SubTask subTask) {
        return SubTaskEntity.builder()
                .id(subTask.getId())
                .name(subTask.getName())
                .build();
    }

    public static SubTaskEntity from(Task task, SubTask subTask) {
        return SubTaskEntity.builder()
                .id(subTask.getId())
                .name(subTask.getName())
                .task(TaskEntity.from(task))
                .build();
    }

    @PrePersist
    public void prePresist() {
        createdAt = LocalDateTime.now();
        lastUpdate = createdAt;
    }

    @PreUpdate
    public void preUpdate() {
        lastUpdate = LocalDateTime.now();
    }
}
