package br.com.financas.popular;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Categoria;
import br.com.financas.util.JPAUtil;

public class PopularCategoria {

	public static void main(String[] args) {

		Categoria c1 = new Categoria("Lazer");
		Categoria c2 = new Categoria("Saúde");
		Categoria c3 = new Categoria("Viagem");
		Categoria c4 = new Categoria("Educação");
		Categoria c5 = new Categoria("Outros");
		
		List<Categoria> categorias = Arrays.asList(c1,c2,c3,c4,c5);
		
		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		
		categorias.forEach(categoria -> {
			em.persist(categoria);
		});

		em.getTransaction().commit();
		em.close();

	}
}
