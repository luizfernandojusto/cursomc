package com.informaticaconsutoria.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.informaticaconsutoria.cursomc.domain.Categoria;
import com.informaticaconsutoria.cursomc.domain.Cidade;
import com.informaticaconsutoria.cursomc.domain.Estado;
import com.informaticaconsutoria.cursomc.domain.Produto;
import com.informaticaconsutoria.cursomc.repositories.CategoriaRepository;
import com.informaticaconsutoria.cursomc.repositories.CidadeRepository;
import com.informaticaconsutoria.cursomc.repositories.EstadoRepository;
import com.informaticaconsutoria.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository prodtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		prodtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		// -------------------------------------------------------------

		Estado est1 = new Estado(null, "MG");
		Estado est2 = new Estado(null, "SP");

		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinhas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));

	}

}
