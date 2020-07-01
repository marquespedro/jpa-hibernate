package br.com.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.financas.dto.MovimentacaoDTO;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class MovimentacoesDao {

	private EntityManager em;

	public MovimentacoesDao(EntityManager em) {
		this.em = em;
	}
	
	
	public  Double retornaMediasMovimentacoesPorTipo(TipoMovimentacao tipo) {

		Double media = null;

		String jpql = "select avg(m.valor) from Movimentacao m where m.tipo = :pTipo ";
		Query query = em.createQuery(jpql);
		query.setParameter("pTipo", tipo);

		media = (Double) query.getSingleResult();

		System.out.println("MEDIA : " + media);

		return media;
	}

	public  BigDecimal retornaSomaMovimentacoes(Conta conta) {

		BigDecimal soma = null;

		EntityManager em = JPAUtil.getEntityManager();

		String jpql = "select sum(m.valor) from Movimentacao m where m.tipo = :pTipo " + " and m.conta = :pConta";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		soma = (BigDecimal) query.getSingleResult();

		System.out.println("SOMA : " + soma);

		return soma;
	}

	public  BigDecimal retornaMaiorValorMovimentacao(Conta conta) {

		BigDecimal maiorValor = null;

		EntityManager em = JPAUtil.getEntityManager();

		String jpql = "select max(m.valor) from Movimentacao m where m.conta = :pConta";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);

		maiorValor = (BigDecimal) query.getSingleResult();

		System.out.println("MAIOR VALOR TRANSACAO : " + maiorValor);

		return maiorValor;
	}

	public  Long retornaQuantidaedMovimentacoes(Conta conta) {

		Long qtd = null;

		String jpql = "select count(m.valor) from Movimentacao m where m.conta = :pConta";
		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);

		qtd = (Long) query.getSingleResult();

		System.out.println("QTD MOVIMENTACAO: " + qtd);

		return qtd;
	}

	public  List<Double> retornaMediaPorDia(Conta conta, TipoMovimentacao tipo) {
		
		List<Double> medias = null;

		String jpql = "select avg(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo" + 
		" group by day(m.data), month(m.data), year(m.data)";

		TypedQuery<Double> query = em.createQuery(jpql, Double.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipo);
		
		medias = query.getResultList();

		System.out.println("MEDIA DIA 20 : " + medias.get(0));
		System.out.println("MEDIA DIA 21 : " + medias.get(1));
		
		
		return medias;
	}

	
	public List<MovimentacaoDTO> retornaConsultaComoDTO(Long idConta){
		
		Conta conta = new Conta(idConta);		
		StringBuilder sql = new StringBuilder();
		
		sql.append("select new br.com.financas.dto.MovimentacaoDTO(m.tipo, m.valor, m.conta) ");
		sql.append("from Movimentacao m ");
		sql.append("join m.conta c ");
		sql.append("where c = :pConta ");
		
		TypedQuery<MovimentacaoDTO> query = em.createQuery(sql.toString() , MovimentacaoDTO.class);
		query.setParameter("pConta", conta);
		
		return   query.getResultList();
	}
	
}
