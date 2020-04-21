package com.informaticaconsutoria.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.informaticaconsutoria.cursomc.domain.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@GetMapping
	public List<Categoria> listar() {

		Categoria c1 = new Categoria(1, "TI");
		Categoria c2 = new Categoria(2, "Desig");

		List<Categoria> list = new ArrayList<>();
		list.add(c1);
		list.add(c2);

		return list;
	}

}
