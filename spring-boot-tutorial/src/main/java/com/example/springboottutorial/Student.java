package com.example.springboottutorial;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
@SQLDelete(sql="UPDATE students SET deleted_at = NOW() WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotBlank
    String firstName;
    @NotBlank
    String lastName;

    @Column(name="ok")
    boolean isAjoke;

    @Column(name="ok1")
    boolean axoke;

    Date createdAt;
    Date updatedAt;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Date deletedAt;

    @PrePersist
    protected void onCreate() {
        this.updatedAt = this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

}
