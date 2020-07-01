package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.dao.ContaDao;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class SimulandoBuscaMovimentacoesLazy {

	public static void main(String[] args) {
		
		
		EntityManager em = JPAUtil.getEntityManager();
		
		em.getTransaction().begin();
		
		Conta conta = new ContaDao(em).buscaContaComMovimentacoesLazy(1L);
		Conta contaCopia = new ContaDao(em).buscaContaComMovimentacoesLazy(1L);
		
		System.out.println(conta.equals(contaCopia));
		
		em.getTransaction().commit();
		em.close();
		
	}
}
