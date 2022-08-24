package com.kuniwake.julio.localizacao;

import com.kuniwake.julio.localizacao.domain.entity.Cidade;
import com.kuniwake.julio.localizacao.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {

	@Autowired
	private CidadeService cidadeService;

	@Override
	public void run(String... args) throws Exception {

//  ********** Exemplo de listagem usando String **********

		// cidadeService.listarCidades();
		// cidadeService.listarCidadesPorNome();
		// cidadeService.listarCidadesPorHabitantes();
		// cidadeService.listarCidadesComecaPor();
		// cidadeService.listarCidadesFinalPor();
		// cidadeService.listarCidadesQueConten();
		// cidadeService.listarCidadesLike();
		// cidadeService.listarCidadesLikeContein();
		// cidadeService.listarCidadesLikeInicia();

// ***********************************************************
//  ********** Exemplo de listagem usando Numeros **********

		// cidadeService.listarCidadesMenosHabitantes();
		// cidadeService.listarCidadesMenosIgualHabitantes();
		// cidadeService.listarCidadesMaisHabitantes();

// ***********************************************************
//  ********** Exemplo de listagem usando Numeros e Strings **********

		 // cidadeService.listarCidadesMenosHabitantesAndNome();

// ***********************************************************
//  ********** Exemplo de listagem usando Paginação **********

		// cidadeService.listarCidadePorNomePaginacao();

		// cidadeService.listarCidadePorNomeOrdenacao();

// ***********************************************************
//  ********** Exemplo de listagem usando Filtro **********

		// var cidade = new Cidade(null, "porto ", null);
		// cidadeService.filtroDinamico(cidade).forEach(System.out::println);

// ***********************************************************
//  ********** Exemplo de listagem usando Specification **********

		// cidadeService.listarCidadeByNomeSpecProperty();
		// cidadeService.listarCidadeByNomeSpec();

// ***********************************************************
// ********** Exemplo de listagem usando Specification com Queries Dinamicas **********

//		var cidade = new Cidade(6L, "Porto Alegre", 50000L);
//		cidadeService.listarCidadeSpecsFiltroDinamico(cidade);

// ***********************************************************
// ********** Exemplo de listagem usando Queries Nativas **********

		// cidadeService.listarCidadePorNomeSQL();
		// cidadeService.listarCidadeApenasPorNomeSQL();

// ***********************************************************
// ********** Exemplo de listagem usando Queries Nativas com Projections **********

		cidadeService.listarCidadePorNomeSQLProjection();
	}







	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}

}
