package com.desafio.cliente.api.mappers;

import com.desafio.cliente.api.dto.*;
import com.desafio.cliente.api.model.Cliente;
import com.desafio.cliente.api.model.Email;
import com.desafio.cliente.api.model.Endereco;
import com.desafio.cliente.api.model.Telefone;
import com.desafio.cliente.api.dto.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    // --- Mapeamentos principais ---

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "removerMascara")
    Cliente toEntity(ClienteRequestDTO dto);
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "formatarMascara")
    ClienteResponseDTO toResponseDTO(Cliente cliente);

    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);

    // --- Sub-mapeamentos auxiliares ---

    List<Telefone> mapTelefones(List<TelefoneDTO> dtos);
    List<TelefoneDTO> mapTelefonesDto(List<Telefone> entities);

    List<Email> mapEmails(List<EmailDTO> dtos);
    List<EmailDTO> mapEmailsDto(List<Email> entities);

    Endereco mapEndereco(EnderecoDTO dto);

    @Mappings({
            @Mapping(source = "cep", target = "cep"),
            @Mapping(source = "logradouro", target = "logradouro"),
            @Mapping(source = "complemento", target = "complemento"),
            @Mapping(source = "bairro", target = "bairro"),
            @Mapping(source = "cidade", target = "cidade"),
            @Mapping(source = "uf", target = "uf")
    })
    EnderecoDTO mapEnderecoDto(Endereco entity);

    // --- Atualização parcial ---

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cpf", source = "cpf", qualifiedByName = "removerMascara")
    void updateClienteFromDTO(ClienteRequestDTO dto, @MappingTarget Cliente cliente);

    // --- Conversores CPF ---

    @Named("formatarMascara")
    default String formatarCpf(String cpf) {
        if (cpf == null || cpf.length() != 11) return cpf;
        return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    @Named("removerMascara")
    default String removerMascaraCpf(String cpf) {
        if (cpf == null) return null;
        return cpf.replaceAll("[^0-9]", "");
    }
}
