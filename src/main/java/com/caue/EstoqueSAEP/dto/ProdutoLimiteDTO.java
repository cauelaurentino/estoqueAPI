/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.caue.EstoqueSAEP.dto;

/**
 *
 * @author digma
 */
public record ProdutoLimiteDTO(
    String produto,
    Integer quantidadeAtual,
    String status,
    String percentualNivel
) {}