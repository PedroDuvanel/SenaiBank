package com.api.senaibank.classe.dto;

import lombok.Data;

@Data
public class ClienteUpdateDTO {

    private Long id;

    private String nome;

    private String telefone;

    private String email;

}
