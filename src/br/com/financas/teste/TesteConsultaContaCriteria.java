package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.dao.ContaDao;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class TesteConsultaContaCriteria {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();

		Conta conta =  new ContaDao(em).consultaContaComCriteria(33L);

		System.out.println(conta.toString());
		
		em.close();

	}

}
