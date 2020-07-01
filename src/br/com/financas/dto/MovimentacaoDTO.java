package br.com.financas.dto;

import java.math.BigDecimal;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MovimentacaoDTO {

	private TipoMovimentacao tipo;
	private BigDecimal valor;
	private Conta conta;
	

	public MovimentacaoDTO(TipoMovimentacao tipo, BigDecimal valor, Conta conta) {
		this.tipo = tipo;
		this.valor = valor;
		this.conta = conta;
	}

	public MovimentacaoDTO() {}

}

