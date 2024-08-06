package com.api.senaibank.classe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.api.senaibank.classe.Transacao;
import com.api.senaibank.classe.service.ContaService;
import com.api.senaibank.classe.service.TransacaoService;

import java.util.List;

@RestController
@RequestMapping("/Extrato")
public class TransacaoController {

    @Autowired
    TransacaoService transacaoService;

    @Autowired
    ContaService contaService;

    @PostMapping
    public ResponseEntity<Transacao> create(@RequestBody Transacao transacao) {
        if(contaService.temSaldo(transacao)){
            return ResponseEntity.ok(transacaoService.create(transacao));
        }
        return ResponseEntity.badRequest().build();
    }
    @GetMapping
    public ResponseEntity<List<Transacao>> getAll(){
        return ResponseEntity.ok(transacaoService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Transacao> getByid(@PathVariable Long id){
        return ResponseEntity.ok(transacaoService.getById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Transacao> update(@PathVariable Long id, @RequestBody Transacao transacao){
        return ResponseEntity.ok(transacaoService.update(id, transacao));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        transacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/extrato/{id}")
    public ResponseEntity<List<Transacao>> getExtrato (@PathVariable Long idConta) {
        List<Transacao> extrato = transacaoService.getExtrato(idConta);
        
        if (extrato.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(extrato);
    }
    
}
