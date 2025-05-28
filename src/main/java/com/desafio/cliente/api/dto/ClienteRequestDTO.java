package com.desafio.cliente.api.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    @Pattern(regexp = "^[\\p{L}\\p{M}\\d ]+$", message = "O nome deve conter apenas letras, números e espaços")
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
