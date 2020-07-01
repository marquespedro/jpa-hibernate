package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Categoria;

public class CategoriaDao {

	private EntityManager em;

	public CategoriaDao(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("unchecked")
	public List<Categoria> buscarCategorias(){
		String sql = "select c from Categoria c";
		Query query = em.createQuery(sql);
		return query.getResultList();
	}
}
