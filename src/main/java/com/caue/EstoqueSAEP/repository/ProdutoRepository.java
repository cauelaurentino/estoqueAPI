/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.caue.EstoqueSAEP.repository;


import com.caue.EstoqueSAEP.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author digma
 */

// REPOSITORY CONECTA EXECUTA FUNÇÕES COMO O CRUD NO BANCO

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query("SELECT p.categoria AS categoria, SUM(p.quantidade * p.valorUnitario) AS valorTotal " +
           "FROM Produto p GROUP BY p.categoria")
            
    List<Map<String, Object>> findValorTotalPorCategoria();
}
