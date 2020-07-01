package br.com.financas.modelo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@NamedQuery(name = "RetornaSomaMovimentacoes", query = "select sum(m.valor) from Movimentacao m where m.tipo = :pTipo  and m.conta = :pConta")
@EqualsAndHashCode(of = {"id", "valor" , "conta"})
@ToString(of = {"id" , "valor" , "tipo"})
@Table(name = "movimentacao" , schema = "public")
public class Movimentacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Enumerated(EnumType.ORDINAL)
	private TipoMovimentacao tipo;

	private BigDecimal valor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idConta")
	private Conta conta;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "Movimentacao_Categoria")
	private Set<Categoria> categorias = new HashSet<>();

	public Movimentacao() {

	}

	public Movimentacao(Date data, TipoMovimentacao tipo, BigDecimal valor, Conta conta, Set<Categoria> categorias) {
		this.data = data;
		this.tipo = tipo;
		this.valor = valor;
		this.conta = conta;
		this.categorias = categorias;
	}

	
}
