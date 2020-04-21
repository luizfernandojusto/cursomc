package com.informaticaconsutoria.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.informaticaconsutoria.cursomc.domain.Categoria;
import com.informaticaconsutoria.cursomc.domain.Cidade;
import com.informaticaconsutoria.cursomc.domain.Cliente;
import com.informaticaconsutoria.cursomc.domain.Endereco;
import com.informaticaconsutoria.cursomc.domain.Estado;
import com.informaticaconsutoria.cursomc.domain.Pagamento;
import com.informaticaconsutoria.cursomc.domain.PagamentoComBoleto;
import com.informaticaconsutoria.cursomc.domain.PagamentoComCartao;
import com.informaticaconsutoria.cursomc.domain.Pedido;
import com.informaticaconsutoria.cursomc.domain.Produto;
import com.informaticaconsutoria.cursomc.domain.enums.EstadoPagamento;
import com.informaticaconsutoria.cursomc.domain.enums.TipoCliente;
import com.informaticaconsutoria.cursomc.repositories.CategoriaRepository;
import com.informaticaconsutoria.cursomc.repositories.CidadeRepository;
import com.informaticaconsutoria.cursomc.repositories.ClienteRepository;
import com.informaticaconsutoria.cursomc.repositories.EnderecoRepository;
import com.informaticaconsutoria.cursomc.repositories.EstadoRepository;
import com.informaticaconsutoria.cursomc.repositories.PagamentoRepository;
import com.informaticaconsutoria.cursomc.repositories.PedidoRepository;
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
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

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

		// -------------------------------------------------------------

		Cliente cli1 = new Cliente(null, "Fernando", "fernando@gmail.com", "123456789", TipoCliente.PESSOAFISCA);
		cli1.getTelefones().addAll(Arrays.asList("(35)9 91025410", "(35)9 99025410"));

		Endereco e1 = new Endereco(null, "Rua Santa Rita1", "137", " Casa1", "Campo1", "37370-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Santa Rita2", "137", " Casa2", "Campo2", "37370-000", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		// -------------------------------------------------------------

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:32"), cli1, e2);

		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"),
				null);
		ped2.setPagamento(pagto2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));

	}

}
