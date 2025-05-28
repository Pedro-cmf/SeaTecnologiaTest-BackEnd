package com.desafio.cliente_api.dto;


import lombok.Getter;
import lombok.Setter;
import java.util.List;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ClienteResponseDTO {

    private Long id;
    private String nome;
    @NotBlank(message = "O cpf é obrigatório")
    private String cpf;
    private EnderecoDTO endereco;
    private List<TelefoneDTO> telefones;
    private List<EmailDTO> emails;

}
