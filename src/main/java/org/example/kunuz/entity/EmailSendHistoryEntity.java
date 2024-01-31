package org.example.kunuz.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "email_send_history")
public class EmailSendHistoryEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "email")
    private String email;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
}
