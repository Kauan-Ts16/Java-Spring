package com.api.TaskSystemAPI.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_tasks")
public class TaskModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "task_id")
    private UUID taskId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @Column(nullable = false, length = 150)
    private String description;

    @CreationTimestamp
    private Instant createdAt;

    private Instant dueDate;

    private boolean completed;
}
