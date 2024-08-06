package com.api.senaibank.classe;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Entity
@Data
@Table(name = "contas_bancarias")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long numeroConta;

    @Column(nullable = false)
    private Double saldo = 0.0;

    @OneToOne
    @JoinColumn(name = "Clientes_id", referencedColumnName = " id")
    @JoinColumn(name = "Cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;

    public void depositar(double valor) {
        saldo += valor;
    }
    public void sacar(double valor) {
        saldo -=valor;
    }

}
