package br.com.financas.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Conta_;

public class ContaDao {

	private EntityManager em;

	public ContaDao(EntityManager em) {
		this.em = em;
	}

	/**
	 * Para testar este, mudar o fetch type para EAGER em Conta
	 * 
	 * @return
	 */
	public Conta buscarContasEager(Long idConta) {
		String sql = "select c from Conta c where c.id = :idConta";
		TypedQuery<Conta> query = em.createQuery(sql, Conta.class);
		query.setParameter("idConta", idConta);
		return query.getSingleResult();
	}

	/**
	 * Retorna conta com movimentacoes inicializada, porem se for realizado um get
	 * nas categorias da movimentacao uma nova query é executada, pois a tabela
	 * associtava movimentacao_categoria possui referencia para movimentacao
	 * 
	 * @return
	 */
	public Conta buscaContaComMovimentacoesLazy(Long idConta) {
		StringBuilder sql = new StringBuilder("select c from Conta c left join fetch c.movimentacoes m ");
		sql.append("join fetch m.categorias ca ");
		sql.append("where c.id = :idConta");
		TypedQuery<Conta> query = em.createQuery(sql.toString(), Conta.class);
		query.setParameter("idConta", idConta);
		return query.getSingleResult();
	}

	public Conta buscarContaMovimentacoesCriteria(Long idConta) {

		CriteriaBuilder cb = this.em.getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);

		Root<Conta> root = cq.from(Conta.class);

		root.fetch("cliente", JoinType.LEFT);
		root.fetch("movimentacoes", JoinType.LEFT);

		cq.select(root);
		cq.where(cb.equal(root.get("id"), idConta));

		Conta conta = em.createQuery(cq).getSingleResult();

		return conta;
	}

	/**
	 * @param idConta
	 * @return conta completa, inicializando tambem as categorias da movimentacao
	 */
	public Conta buscarContaCompleta(Long idConta) {

		StringBuilder sql = new StringBuilder();
		sql.append("select c from Conta c ");
		sql.append("left join fetch c.movimentacoes m ");
		sql.append("left join fetch m.categorias categoria ");
		sql.append("left join fetch categoria.movimentacoes ");
		sql.append("left join fetch c.cliente cliente ");
		sql.append("where c.id = :idConta ");

		TypedQuery<Conta> query = em.createQuery(sql.toString(), Conta.class);
		query.setParameter("idConta", idConta);

		return query.getSingleResult();

	}

	public Conta consultaContaComCriteria(Long idConta) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);
		Root<Conta> root = cq.from(Conta.class);
		root.fetch(Conta_.movimentacoes, JoinType.LEFT);
		cq.where(cb.equal(root.get("id"), idConta));
		TypedQuery<Conta> query = em.createQuery(cq);
	
		return query.getSingleResult();
	}
}
