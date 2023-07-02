package com.example.entitymanagerrest.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeDto implements Serializable {

    @Size(max = 20)
    @NotNull
    private String name;

    @NotNull
    private Integer age;

    @Size(max = 50)
    @NotNull
    private String email;

    @Size(max = 20)
    @NotNull
    private String nickname;


}
