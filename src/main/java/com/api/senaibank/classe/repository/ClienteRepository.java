package com.api.senaibank.classe.repository;

import com.api.senaibank.classe.Cliente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByAtivoTrue();
    
    @Query("SELECT new com.api.senai.dto.ClienteDTO(c.id, c.nome) FROM Cliente c")
    List<Cliente> getClientesDTO();
    
}
