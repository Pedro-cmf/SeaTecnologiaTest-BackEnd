package com.desafio.cliente.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    private Long id;

    @Email(message = "O e-mail deve ser válido")
    @NotBlank(message = "E-mail é obrigatório")
    private String email;
}