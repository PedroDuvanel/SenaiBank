package com.api.senaibank.classe.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.api.senaibank.classe.Cliente;
import com.api.senaibank.classe.dto.ClienteDTO;
import com.api.senaibank.classe.dto.ClienteUpdateDTO;
import com.api.senaibank.classe.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> getAll() {
        return clienteRepository.findAll();
    }
    public List<Cliente> getAllAtivos(){
        return clienteRepository.findByAtivoTrue();
    }

    public Cliente getById(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente atualizarCliente(Cliente clienteSalvo, Cliente clienteNovo) {

        if (clienteNovo.getNome() != null) {
            clienteSalvo.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getCpf() != null) {
            clienteSalvo.setCpf(clienteNovo.getCpf());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteSalvo.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getDatanascimento() != null) {
            clienteSalvo.setDatanascimento(clienteNovo.getDatanascimento());
        }
        if (clienteNovo.getEndereco() != null) {
            clienteSalvo.setEndereco(clienteNovo.getEndereco());
        }
        if (clienteNovo.getEmail() != null) {
            clienteSalvo.setEmail(clienteNovo.getEmail());  
        }
        if (clienteNovo.isAtivo() == false) {
            clienteSalvo.setAtivo(false);
        }

        return clienteRepository.save(clienteSalvo);
    }

    public Cliente delete(Long id) {
        
        // clienteRepository.deleteById(id);

        Cliente cliente = getById(id);

        Cliente clienteInativo = new Cliente();
        clienteInativo.setAtivo(false);

        return atualizarCliente(cliente, clienteInativo);

    }

      public ClienteUpdateDTO updateDTO(Cliente clienteExistente, ClienteUpdateDTO clienteNovo) {

        // Converter o que é DTO pra Cliente

        if (clienteNovo.getNome() != null) {
            clienteExistente.setNome(clienteNovo.getNome());
        }
        if (clienteNovo.getTelefone() != null) {
            clienteExistente.setTelefone(clienteNovo.getTelefone());
        }
        if (clienteNovo.getEmail() != null) {
            clienteExistente.setEmail(clienteNovo.getEmail());
        }

        // Atualizar o clienteExistente com os dados do clienteNovo
        Cliente clienteSalvo = clienteRepository.save(clienteExistente);

        // Converter o Cliente pra DTO 
        ClienteUpdateDTO clienteDTO = new ClienteUpdateDTO();
        clienteDTO.setId(clienteSalvo.getId());
        clienteDTO.setNome(clienteSalvo.getNome());
        clienteDTO.setTelefone(clienteSalvo.getTelefone());
        clienteDTO.setEmail(clienteSalvo.getEmail());

        // Retornar
        return clienteDTO;
    }

    public List<ClienteDTO> getClientesDTO() {

        List<Cliente> clientes = clienteRepository.findAll();

        List<ClienteDTO> clientesDTO = new ArrayList<>();

        for (Cliente cliente : clientes) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNome(cliente.getNome());

            clientesDTO.add(clienteDTO);
        }
        return clientesDTO;
    }
}
