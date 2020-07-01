package br.com.financas.dto;

import java.util.HashSet;
import java.util.Set;

import br.com.financas.modelo.Cliente;
import br.com.financas.modelo.Movimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContaDTO {

	private Long id;
	private Set<Movimentacao> movimentacoes = new HashSet<>();
	private Cliente cliente;

}
