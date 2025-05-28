package com.desafio.cliente_api.mappers;

import com.desafio.cliente_api.dto.*;
import com.desafio.cliente_api.model.Cliente;
import com.desafio.cliente_api.model.Email;
import com.desafio.cliente_api.model.Endereco;
import com.desafio.cliente_api.model.Telefone;
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

    ClienteResponseDTO toResponseDTO(Cliente cliente);

    List<ClienteResponseDTO> toResponseDTOList(List<Cliente> clientes);

    // --- Sub-mapeamentos auxiliares ---

    List<Telefone> mapTelefones(List<TelefoneDTO> dtos);
    List<TelefoneDTO> mapTelefonesDto(List<Telefone> entities);

    List<Email> mapEmails(List<EmailDTO> dtos);
    List<EmailDTO> mapEmailsDto(List<Email> entities);

    Endereco mapEndereco(EnderecoDTO dto);
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
