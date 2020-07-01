package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class TesteBuscaMovimentacoes {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();

		/*
		 * 
		 * Desta forma o hibernate ira gerar o famos N+1 , onde ele ira fazer um select
		 * nas contas e depois ira fazer um select para cada movimentacao
		 * 
		 * String jpql = "select c from Conta c";
		 * 
		 * 
		 * Desta forma ira consultar tudo em uma unica query
		 * 
		 * String jpql = "select c from Conta c join fetch c.movimentacoes";
		 * 
		 * O Join Fetch elimina a necessidade do N+1, quando ele buscar a conta ja ira
		 * trazer as movimentacoes
		 * 
		 * 
		 */

		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";
		TypedQuery<Conta> query = em.createQuery(jpql, Conta.class);

		List<Conta> resultado = query.getResultList();

		for (Conta c : resultado) {
			System.out.println("CLIENTE : " + c.getCliente().getNome());
			System.out.println("CONTA ID: " + c.getId());
			System.out.println("MOVIMENTACOES" + c.getMovimentacoes());
		}

		em.close();

	}

}
