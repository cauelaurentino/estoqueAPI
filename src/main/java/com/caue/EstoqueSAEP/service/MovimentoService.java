package com.caue.EstoqueSAEP.service;

import com.caue.EstoqueSAEP.model.Movimento;
import com.caue.EstoqueSAEP.model.Produto;
import com.caue.EstoqueSAEP.repository.MovimentoRepository;
import com.caue.EstoqueSAEP.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovimentoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentoRepository movimentacaoRepository;

    @Transactional
    public Movimento registrarEntrada(Long produtoId, Integer quantidade, LocalDate dataMovimentacao) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setQuantidade(produto.getQuantidade() + quantidade);
        produtoRepository.save(produto);

        Movimento mov = new Movimento();
        mov.setProduto(produto);
        mov.setTipo("ENTRADA");
        mov.setQuantidade(quantidade);
        mov.setDataMovimentacao(dataMovimentacao);
        mov.setValorTotal(quantidade * produto.getValorUnitario());

        return movimentacaoRepository.save(mov);
    }

    @Transactional
    public Movimento registrarSaida(Long produtoId, Integer quantidade, LocalDate dataMovimentacao) {
        Produto produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (produto.getQuantidade() < quantidade) {
            throw new RuntimeException("Saldo insuficiente em estoque!");
        }

        produto.setQuantidade(produto.getQuantidade() - quantidade);
        produtoRepository.save(produto);

        Movimento mov = new Movimento();
        mov.setProduto(produto);
        mov.setTipo("SAIDA");
        mov.setQuantidade(quantidade);
        mov.setDataMovimentacao(dataMovimentacao);
        mov.setValorTotal(quantidade * produto.getValorUnitario());

        return movimentacaoRepository.save(mov);
    }

    public List<Movimento> listarSaidasDecrescentes() {
        return movimentacaoRepository.findByTipoOrderByDataMovimentacaoDesc("SAIDA");
    }
}