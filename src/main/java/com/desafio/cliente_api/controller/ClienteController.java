package com.desafio.cliente_api.controller;

import com.desafio.cliente_api.dto.ClienteRequestDTO;
import com.desafio.cliente_api.dto.ClienteResponseDTO;
import com.desafio.cliente_api.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> criarCliente(@Valid @RequestBody ClienteRequestDTO clienteDTO) {
        ClienteResponseDTO clienteSalvo = clienteService.criarCliente(clienteDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }
    @GetMapping
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("API de Clientes está funcionando corretamente.");
    }

}
