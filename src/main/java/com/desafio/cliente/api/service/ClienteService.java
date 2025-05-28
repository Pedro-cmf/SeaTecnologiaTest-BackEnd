package com.desafio.cliente.api.service;

import com.desafio.cliente.api.dto.ClienteResponseDTO;
import com.desafio.cliente.api.dto.ViaCepResponseDTO;
import com.desafio.cliente.api.model.Cliente;
import com.desafio.cliente.api.model.Endereco;
import com.desafio.cliente.api.repository.ClienteRepository;
import com.desafio.cliente.api.dto.ClienteRequestDTO;
import com.desafio.cliente.api.mappers.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ViaCepService viaCepService;
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

        // Busca endereço pelo CEP na API ViaCEP
        String cep = dto.getEndereco().getCep();
        ViaCepResponseDTO enderecoViaCep = viaCepService.buscarEndereco(cep);

        Endereco endereco = new Endereco();
        endereco.setCep(enderecoViaCep.getCep());
        endereco.setLogradouro(enderecoViaCep.getLogradouro());
        endereco.setComplemento(enderecoViaCep.getComplemento());
        endereco.setBairro(enderecoViaCep.getBairro());
        endereco.setCidade(enderecoViaCep.getLocalidade());
        endereco.setUf(enderecoViaCep.getUf());
        endereco.setCliente(cliente);

        cliente.setEndereco(endereco);

        Cliente clienteSalvo = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(clienteSalvo);
    }

    public List<ClienteResponseDTO> listarTodosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toResponseDTOList(clientes);
    }

    public ClienteResponseDTO buscarClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
        return clienteMapper.toResponseDTO(cliente);
    }

}
