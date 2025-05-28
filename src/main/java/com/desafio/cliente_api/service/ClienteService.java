package com.desafio.cliente_api.service;

import com.desafio.cliente_api.dto.ClienteRequestDTO;
import com.desafio.cliente_api.dto.ClienteResponseDTO;
import com.desafio.cliente_api.mappers.ClienteMapper;
import com.desafio.cliente_api.model.Cliente;
import com.desafio.cliente_api.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ClienteService {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteRepository clienteRepository;

    @Transactional
    public ClienteResponseDTO criarCliente(ClienteRequestDTO dto) {
        // --- Validações de Negócio (Exemplos) ---
        // Adicione aqui validações como verificar se CPF já existe, etc.
        // Ex: if (clienteRepository.existsByCpf(clienteMapper.removerMascaraCpf(dto.getCpf()))) { ... }

        if (dto.getTelefones() == null || dto.getTelefones().isEmpty()) {
            throw new IllegalArgumentException("É necessário cadastrar pelo menos um telefone.");
        }

        if (dto.getEmails() == null || dto.getEmails().isEmpty()) {
            throw new IllegalArgumentException("É necessário cadastrar pelo menos um e-mail.");
        }

        // --- Mapeamento DTO -> Entidade ---
        Cliente cliente = clienteMapper.toEntity(dto);

        // --- Persistência ---
        Cliente clienteSalvo = clienteRepository.save(cliente);

        // --- Mapeamento Entidade -> Response DTO ---
        return clienteMapper.toResponseDTO(clienteSalvo);
    }
}
