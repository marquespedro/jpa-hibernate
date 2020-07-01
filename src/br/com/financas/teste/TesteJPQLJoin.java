package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.util.JPAUtil;

public class TesteJPQLJoin {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();

//		Conta conta = new Conta(1L);
		Categoria categoria = new Categoria();
		
		String jpql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";
		Query query = em.createQuery(jpql);
		query.setParameter("pCategoria",categoria );
	
		@SuppressWarnings("unchecked")
		List<Movimentacao> resultado = query.getResultList();

		for (Movimentacao m : resultado) {
			System.out.println(m);
		}

		em.close();

	}

}
