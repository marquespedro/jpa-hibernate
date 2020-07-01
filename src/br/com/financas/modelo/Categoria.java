package br.com.financas.modelo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String descricao;

	@ManyToMany(mappedBy = "categorias" , fetch = FetchType.LAZY)
	private List<Movimentacao> movimentacoes;

	public Categoria() {

	}
	public Categoria(String descricao) {
		this.descricao = descricao;
	}
}
