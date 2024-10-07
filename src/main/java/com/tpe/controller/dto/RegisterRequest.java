package com.tpe.controller.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;


@Data // getter + setter + allargs+ noargs + hash + toString
public class RegisterRequest {

    @NotBlank
    @NotNull
    @Size(min = 1, max = 15, message = "Last name '${validatedValue}' must be between {min} and {max}")
    private String firstName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 15, message = "Last name '${validatedValue}' must be between {min} and {max}")
    private String lastName;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 20, message = "User name '${validatedValue}' must be between {min} and {max}")
    private String userName;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 20, message = "Password '${validatedValue}' must be between {min} and {max}")
    private String password;


    private Set<String> roles;


}
