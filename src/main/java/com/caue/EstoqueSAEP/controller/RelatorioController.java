package com.caue.EstoqueSAEP.controller;

import com.caue.EstoqueSAEP.dto.RelatorioMaiorSaidaDTO;
import com.caue.EstoqueSAEP.dto.RelatorioPeriodoDTO;
import com.caue.EstoqueSAEP.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    // ALTERADO: Agora retorna List<RelatorioPeriodoDTO>
    @GetMapping("/periodo")
    public List<RelatorioPeriodoDTO> relatorioPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return relatorioService.relatorioPeriodo(dataInicio, dataFim);
    }

    // ALTERADO: Agora retorna List<RelatorioMaiorSaidaDTO>
    @GetMapping("/maior-saida")
    public List<RelatorioMaiorSaidaDTO> relatorioMaiorSaida(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
        return relatorioService.relatorioMaiorSaida(dataInicio, dataFim);
    }
}