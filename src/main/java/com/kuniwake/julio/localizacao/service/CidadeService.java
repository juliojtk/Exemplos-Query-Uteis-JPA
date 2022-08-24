package com.kuniwake.julio.localizacao.service;

import com.kuniwake.julio.localizacao.domain.entity.Cidade;
import com.kuniwake.julio.localizacao.doman.repositories.CidadeRepository;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.kuniwake.julio.localizacao.doman.repositories.specs.CidadeSpecs.*;

@Service
public class CidadeService {

    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository){
        this.cidadeRepository = cidadeRepository;
    }

//  ********** Exemplo de listagem usando Strings **********

    public void listarCidades(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    public void listarCidadesPorNome(){
        cidadeRepository.findByNome("Porto Velho").forEach(System.out::println);
    }

    public void listarCidadesComecaPor(){cidadeRepository.findByNomeStartingWith("Belo").forEach(System.out::println);}

    public void listarCidadesQueConten(){cidadeRepository.findByNomeContaining("a").forEach(System.out::println);}

    public void listarCidadesFinalPor(){cidadeRepository.findByNomeEndingWith("a").forEach(System.out::println);}

    public void listarCidadesLike(){cidadeRepository.findByNomeLike("%za").forEach(System.out::println);}

    public void listarCidadesLikeContein(){cidadeRepository.findByNomeLike("%a%").forEach(System.out::println);}

    public void listarCidadesLikeInicia(){cidadeRepository.findByNomeLike("porto%").forEach(System.out::println);}


//  ********** Exemplo de listagem usando NUMEROS **********

    public void listarCidadesPorHabitantes(){cidadeRepository.findByHabitantes(8000000L).forEach(System.out::println);}

    public void listarCidadesMenosHabitantes(){cidadeRepository.findByHabitantesLessThan(1000001L).forEach(System.out::println);}

    public void listarCidadesMenosIgualHabitantes(){cidadeRepository.findByHabitantesLessThanEqual(1000000L).forEach(System.out::println);}

    public void listarCidadesMaisHabitantes(){cidadeRepository.findByHabitantesGreaterThan(1000001L).forEach(System.out::println);}


//  ********** Exemplo de listagem usando NUMEROS e STRING **********

    public void listarCidadesMenosHabitantesAndNome(){cidadeRepository.findByHabitantesLessThanAndNomeLike(1000001L, "Br%").forEach(System.out::println);}


//  ********** Exemplo de listagem usando PAGINAÇÃO, ORDENAÇÂO **********


    public void listarCidadePorNomePaginacao(){
        Pageable pageable = PageRequest.of(0, 1);
        cidadeRepository.findByNomeLikePaginacao("Porto%", pageable).forEach(System.out::println);
    }

    public void listarCidadePorNomeOrdenacao(){
        cidadeRepository.findByNomeLikeOrdenado("Porto%", Sort.by("habitantes")).forEach(System.out::println);
    }


//  ********** Exemplo de listagem usando FILTRO **********

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING); // Igual Like %nome
        Example<Cidade> cidadeExample = Example.of(cidade, matcher);

        return cidadeRepository.findAll(cidadeExample);
    }


//  ********** Exemplo de listagem usando SPECIFICATIONS **********

    public void listarCidadeByNomeSpecProperty(){
        cidadeRepository
                .findAll(propertyEqual("nome", "São Paulo").and(propertyEqual("habitantes",  12396372)))
                .forEach(System.out::println);
    }

    public void listarCidadeByNomeSpec(){
        cidadeRepository
                .findAll(nomeEqual("São Paulo").and(habitantesGreaterThan(100000L)))
                .forEach(System.out::println);
    }


//  ********** Exemplo de listagem usando SPECIFICATIONS com Queries Dinamicas **********

    public void listarCidadeSpecsFiltroDinamico(Cidade filtro){
        Specification<Cidade> specification = Specification.where((root, query, cb) -> cb.conjunction());

        if (filtro.getId() != null){
            specification = specification.and(idEqual(filtro.getId()));
        }

        if (StringUtils.hasText(filtro.getNome())){
            specification = specification.and(nomeLike(filtro.getNome()));
        }

        if(filtro.getHabitantes() != null){
            specification = specification.and(habitantesGreaterThan(filtro.getHabitantes()));
        }

        cidadeRepository.findAll(specification).forEach(System.out::println);
    }


//  ********** Exemplo de listagem usando SQL nativa **********

    public void listarCidadePorNomeSQL(){
        cidadeRepository
                .findByNomeSqlNativa("Belo Horizonte")
                .forEach(System.out::println);
    }

    public void listarCidadeApenasPorNomeSQL(){
        cidadeRepository
                .findByOnlyNomeSqlNativa("Belo Horizonte")
                .forEach(System.out::println);
    }


//  ********** Exemplo de listagem usando SQL nativa com Projections **********

    public void listarCidadePorNomeSQLProjection(){
        cidadeRepository
                .findByNomeSqlNativaProjection("Belo Horizonte")
                // Transformando o objeto cidadeProjection e Cidade
                .stream().map(cidadeProjection -> new Cidade(cidadeProjection.getId(), cidadeProjection.getNome(), null))
                .forEach(System.out::println);
    }
}
