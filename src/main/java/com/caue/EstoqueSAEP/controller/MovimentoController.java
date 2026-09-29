package com.caue.EstoqueSAEP.controller;

import com.caue.EstoqueSAEP.model.Movimento;
import com.caue.EstoqueSAEP.service.MovimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/movimentos")
public class MovimentoController {

    @Autowired
    private MovimentoService estoqueService;

    @PostMapping("/entrada") // http://localhost:8080/estoque/api/movimentos/entrada?produtoId=2&quantidade=10&data=2026-08-10
    public ResponseEntity<Movimento> registrarEntrada(
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(estoqueService.registrarEntrada(produtoId, quantidade, data));
    }

    @PostMapping("/saida") // http://localhost:8080/estoque/api/movimentos/saida?produtoId=2&quantidade=10&data=2026-08-10
    public ResponseEntity<Movimento> registrarSaida(
            @RequestParam Long produtoId,
            @RequestParam Integer quantidade,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return ResponseEntity.ok(estoqueService.registrarSaida(produtoId, quantidade, data));
    }

    @GetMapping("/saidas")
    public List<Movimento> listarSaidas() {
        return estoqueService.listarSaidasDecrescentes();
    }
}