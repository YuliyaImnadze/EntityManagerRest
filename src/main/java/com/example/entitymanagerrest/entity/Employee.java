package com.example.entitymanagerrest.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 20)
    @NotNull
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Size(max = 50)
    @NotNull
    @Column(name = "email", length = 50)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "nickname", nullable = false, length = 20)
    private String nickname;

}
