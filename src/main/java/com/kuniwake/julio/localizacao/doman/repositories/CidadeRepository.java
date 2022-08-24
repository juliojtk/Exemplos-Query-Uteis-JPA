package com.kuniwake.julio.localizacao.doman.repositories;

import com.kuniwake.julio.localizacao.domain.entity.Cidade;
import com.kuniwake.julio.localizacao.doman.repositories.projections.CidadeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {

// ************ Exemplos com Strings ************
    List<Cidade> findByNome(String nome);
    List<Cidade> findByNomeStartingWith(String nome);
    List<Cidade> findByNomeEndingWith(String nome);
    List<Cidade> findByNomeContaining(String nome);
    @Query("SELECT c FROM Cidade c WHERE UPPER(c.nome) LIKE UPPER(?1)")
    List<Cidade> findByNomeLike(String nome); // Usando query method para poder traser consultas tanto minusculas quanto maiusculas


// ************ Exemplos com Numeros ************

    List<Cidade> findByHabitantes(Long habitantes);
    List<Cidade> findByHabitantesLessThan(Long habitantes);
    List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
    List<Cidade> findByHabitantesGreaterThan(Long habitantes);


// ************ Exemplos com Strings e Numeros ************
    List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);


// ************ Exemplos com Paginação, Ordenado ************

    @Query("SELECT c FROM Cidade c WHERE UPPER(c.nome) LIKE UPPER(?1)")
    Page<Cidade> findByNomeLikePaginacao(String nome, Pageable pageable);

    @Query("SELECT c FROM Cidade c WHERE UPPER(c.nome) LIKE UPPER(?1)")
    List<Cidade> findByNomeLikeOrdenado(String nome, Sort sort);


// ************ Exemplos de query nativas ************

    @Query(nativeQuery = true, value = "SELECT * FROM tb_cidade as c WHERE c.nome = :nome ")
    List<Cidade> findByNomeSqlNativa(@Param("nome") String nome);

    @Query(nativeQuery = true, value = "SELECT c.nome FROM tb_cidade as c WHERE c.nome = :nome ")
    List<String> findByOnlyNomeSqlNativa(@Param("nome") String nome);


// ************ Exemplos de query nativas usando Projections ************

    @Query(nativeQuery = true, value = "SELECT c.id_cidade as id, c.nome FROM tb_cidade as c WHERE c.nome = :nome ")
    List<CidadeProjection> findByNomeSqlNativaProjection(@Param("nome") String nome);

}
