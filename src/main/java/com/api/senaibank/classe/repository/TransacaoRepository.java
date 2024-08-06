package com.api.senaibank.classe.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.senaibank.classe.Conta;
import com.api.senaibank.classe.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao,Long>{
    List<Transacao> findByContaOrigem_NumContaAndDataBetween(String numConta, LocalDate startDate, LocalDate endDate);
    List<Transacao> findByContaOrigemOrContaDestinoOrderByDataDesc(
        Conta contaOrigem, 
        Conta contaDestino
    );
}