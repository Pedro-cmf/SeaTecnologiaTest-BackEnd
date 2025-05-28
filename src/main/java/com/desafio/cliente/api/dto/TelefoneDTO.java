package com.desafio.cliente.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDTO {

    private Long id;

    @NotBlank(message = "O número do telefone é obrigatório")
    @Pattern(regexp = "\\d{10,11}", message = "O telefone deve conter entre 10 e 11 dígitos numéricos")
    private String telefones;

    @NotBlank(message = "O tipo de telefone é obrigatório")
    @Pattern(regexp = "CELULAR|RESIDENCIAL|COMERCIAL", message = "Tipo de telefone inválido")
    private String tipo;


}
