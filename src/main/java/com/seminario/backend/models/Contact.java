package com.seminario.backend.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Getter()
@Setter()
@NoArgsConstructor()
@Entity(name = "contact")
@SQLDelete(sql = "UPDATE contact SET deleted_at = true WHERE id=?")
@Where(clause = "deleted_at = false")
public class Contact {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column()
    private String name;

    @Column()
    private String email;

    @Column()
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "deleted_at")
    boolean deleatAt;

    public Contact(String name, String email, String phone, Users user) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.user = user;
    }
}
