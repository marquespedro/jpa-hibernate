package br.com.financas.modelo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.financas.converter.DadoContaConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = {"id", "banco", "agencia", "numero"})
@ToString(of = {"id","numero" , "movimentacoes"})
@Table(name = "conta" , schema = "public")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "banco")
	private String banco;
	
	@Column(name = "agencia")	
	private String agencia;
	
	@Column(name = "numero")	
	@Convert(converter = DadoContaConverter.class)
	private String numero;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="conta")
	private Set<Movimentacao> movimentacoes = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Conta() {

	}
	public Conta(Long id, Cliente cliente, Set<Movimentacao> movimentacoes) {
		this.id = id;
		this.cliente = cliente;
		this.movimentacoes = movimentacoes;
	}
	
	public Conta(Long id) {
		this.id = id;
	}

	public Conta(String banco, String agencia, String numero, Cliente cliente) {
		super();
		this.banco = banco;
		this.agencia = agencia;
		this.numero = numero;
		this.cliente = cliente;
	}
	

	
}

