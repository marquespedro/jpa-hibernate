package br.com.financas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.financas.modelo.Cliente;

public class ClienteDao {

	private EntityManager em;

	public ClienteDao(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodosClientes(){
		String sql = "select c from Cliente c";
		Query query = this.em.createQuery(sql);
		return query.getResultList();
	}
}
