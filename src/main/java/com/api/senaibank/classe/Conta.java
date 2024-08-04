package com.api.senaibank.classe;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "id")
@Entity
@Data
@Table(name = "contas")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;

    @Column(name = "numConta", unique = true, nullable = false)
    private String numConta;

    @Column(nullable = false)
    private Double saldo = 0.0;

    @OneToOne
    @JoinColumn(name = "Clientes_id", referencedColumnName = " id")
    private Cliente cliente;

    public boolean temSaldo(double valor){
        if(this.getSaldo() >= valor) {
            return true;
        } 
        return false;
    }

}
