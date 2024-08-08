package com.api.senaibank.classe;

import java.time.LocalDate;

import com.api.senaibank.classe.tipotransacao.TipoTransacao;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "transacoes")
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated
    private TipoTransacao tipoTransacao;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "conta_origem", referencedColumnName = "numConta")
    private Conta contaOrigem;

    @ManyToOne
    @JoinColumn(name = "conta_destino", referencedColumnName = "numConta")
    private Conta contaDestino;


}
