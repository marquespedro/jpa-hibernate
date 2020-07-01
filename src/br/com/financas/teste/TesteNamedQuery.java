package br.com.financas.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class TesteNamedQuery {

	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();

		TypedQuery<BigDecimal> query = em.createNamedQuery("RetornaSomaMovimentacoes", BigDecimal.class);

		query.setParameter("pConta", new Conta(2L));
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);

		BigDecimal soma = query.getSingleResult();
		System.out.println("SOMA : " + soma);

		em.close();

	}

}
