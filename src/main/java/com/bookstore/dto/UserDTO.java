package com.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

/**
 * @author Muralitharan R K
 * @project javakata-bookstore-api
 */

@Data
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {

    @Column(name = "username")
    @NotBlank(message = "Username is mandatory")
    private String username;

    @Column(name = "password")
    @NotBlank(message = "Password is mandatory")
    private String password;
}
