package com.caue.EstoqueSAEP.repository;

import com.caue.EstoqueSAEP.dto.RelatorioMaiorSaidaDTO;
import com.caue.EstoqueSAEP.dto.RelatorioPeriodoDTO;
import com.caue.EstoqueSAEP.model.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MovimentoRepository extends JpaRepository<Movimento, Long> {

    List<Movimento> findByTipoOrderByDataMovimentacaoDesc(String tipo);

    @Query("SELECT new com.caue.EstoqueSAEP.dto.RelatorioPeriodoDTO(" +
           "m.produto.nome, m.produto.unidadeMedida, " +
           "SUM(CASE WHEN m.tipo = 'ENTRADA' THEN m.quantidade ELSE 0 END), " +
           "SUM(CASE WHEN m.tipo = 'SAIDA' THEN m.quantidade ELSE 0 END), " +
           "(SUM(CASE WHEN m.tipo = 'ENTRADA' THEN m.quantidade ELSE 0 END) - SUM(CASE WHEN m.tipo = 'SAIDA' THEN m.quantidade ELSE 0 END)), " +
           "SUM(CASE WHEN m.tipo = 'ENTRADA' THEN m.valorTotal ELSE 0.0 END), " +
           "SUM(CASE WHEN m.tipo = 'SAIDA' THEN m.valorTotal ELSE 0.0 END)) " +
           "FROM Movimento m " +
           "WHERE m.dataMovimentacao BETWEEN :dataInicio AND :dataFim " +
           "GROUP BY m.produto.nome, m.produto.unidadeMedida")
    List<RelatorioPeriodoDTO> relatorioMovimentacaoPeriodo(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);

    @Query("SELECT new com.caue.EstoqueSAEP.dto.RelatorioMaiorSaidaDTO(" +
           "m.produto.nome, SUM(m.quantidade), SUM(m.valorTotal)) " +
           "FROM Movimento m " +
           "WHERE m.tipo = 'SAIDA' AND m.dataMovimentacao BETWEEN :dataInicio AND :dataFim " +
           "GROUP BY m.produto.nome " +
           "ORDER BY SUM(m.quantidade) DESC")
    List<RelatorioMaiorSaidaDTO> relatorioMaiorVolumeSaida(@Param("dataInicio") LocalDate dataInicio, @Param("dataFim") LocalDate dataFim);
}