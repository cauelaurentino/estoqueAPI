package com.caue.EstoqueSAEP.service;

import com.caue.EstoqueSAEP.dto.RelatorioMaiorSaidaDTO;
import com.caue.EstoqueSAEP.dto.RelatorioPeriodoDTO;
import com.caue.EstoqueSAEP.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private MovimentoRepository movimentacaoRepository;

    public List<RelatorioPeriodoDTO> relatorioPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return movimentacaoRepository.relatorioMovimentacaoPeriodo(dataInicio, dataFim);
    }

    public List<RelatorioMaiorSaidaDTO> relatorioMaiorSaida(LocalDate dataInicio, LocalDate dataFim) {
        return movimentacaoRepository.relatorioMaiorVolumeSaida(dataInicio, dataFim);
    }
}