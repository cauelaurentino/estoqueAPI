package com.caue.EstoqueSAEP.controller;

import com.caue.EstoqueSAEP.dto.ProdutoLimiteDTO;
import com.caue.EstoqueSAEP.model.Produto;
import com.caue.EstoqueSAEP.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto) {
        return ResponseEntity.ok(produtoService.cadastrarProduto(produto));
    }

    @GetMapping("/categoria-total")
    public List<Map<String, Object>> valorTotalPorCategoria() {
        return produtoService.listarValorTotalPorCategoria();
    }

    // ALTERADO: Agora retorna List<ProdutoLimiteDTO>
    @GetMapping("/limites")
    public List<ProdutoLimiteDTO> limitesEstoque() {
        return produtoService.verificarLimitesEstoque();
    }
}