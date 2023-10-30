package com.seminario.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;

@Getter()
@Setter()
@NoArgsConstructor
@Entity(name = "task")
@SQLDelete(sql = "UPDATE task SET deleted_at = true WHERE id=?")
@Where(clause = "deleted_at = false")
public class Task {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "deleted_at")
    boolean deletedAt;

    @ManyToOne
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    public Task(String name, String description, Date startDate, Date endDate, Users user, Contact contact) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.contact = contact;
    }
}
