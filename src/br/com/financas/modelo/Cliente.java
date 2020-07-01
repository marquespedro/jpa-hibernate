package br.com.financas.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(of = {"id" , "nome", "endereco" , "cpf"})
@ToString(of = {"id","nome"})
@Table(name = "cliente" , schema = "public")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String endereco;

	@Column(unique = true)
	private String cpf;

	public Cliente(String nome, String cpf, String endereco) {
		this.endereco = endereco;
		this.cpf = cpf;
		this.nome = nome;
	}

	public Cliente(Long id) {
		this.id = id;
	}
	
	public Cliente() {

	}



}
