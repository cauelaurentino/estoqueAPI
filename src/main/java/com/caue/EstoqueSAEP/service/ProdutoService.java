package com.caue.EstoqueSAEP.service;

import com.caue.EstoqueSAEP.model.Produto;
import com.caue.EstoqueSAEP.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caue.EstoqueSAEP.dto.ProdutoLimiteDTO;

import java.util.*;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Produto cadastrarProduto(Produto produto) {
        if (produto.getQuantidade() == null) {
            produto.setQuantidade(0);
        }
        return produtoRepository.save(produto);
    }

    public List<Map<String, Object>> listarValorTotalPorCategoria() {
        return produtoRepository.findValorTotalPorCategoria();
    }

    public List<ProdutoLimiteDTO> verificarLimitesEstoque() {
    List<Produto> produtos = produtoRepository.findAll();
    List<ProdutoLimiteDTO> alertas = new ArrayList<>();

    for (Produto p : produtos) {
        double percentual = (p.getQuantidade() / 100.0) * 100.0;
        if (p.getQuantidade() <= 0 || p.getQuantidade() >= 100) {
            String status = p.getQuantidade() <= 0 ? "LIMITE MÍNIMO ATINGIDO" : "LIMITE MÁXIMO ATINGIDO";
            String percentualFormatado = String.format("%.2f%%", percentual);
            
            alertas.add(new ProdutoLimiteDTO(p.getNome(), p.getQuantidade(), status, percentualFormatado));
        }
    }
    return alertas;
}
}