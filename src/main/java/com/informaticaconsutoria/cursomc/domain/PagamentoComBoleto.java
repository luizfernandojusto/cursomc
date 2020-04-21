package com.informaticaconsutoria.cursomc.domain;

import java.util.Date;

import com.informaticaconsutoria.cursomc.domain.enums.EstadoPagamento;

public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Date dataVecimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVecimento,
			Date dataPagamento) {
		super(id, estado, pedido);		
		this.dataVecimento = dataVecimento;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVecimento() {
		return dataVecimento;
	}

	public void setDataVecimento(Date dataVecimento) {
		this.dataVecimento = dataVecimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}
