package com.desafio.cliente_api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório")
    private String cpf;

    @NotEmpty(message = "É necessário cadastrar pelo menos um telefone.")
    @Valid
    private List<TelefoneDTO> telefones;

    @NotEmpty(message = "É necessário cadastrar pelo menos um e-mail.")
    @Valid
    private List<EmailDTO> emails;

    @Valid
    private EnderecoDTO endereco;
}
