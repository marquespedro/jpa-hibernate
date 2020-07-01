package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.dao.ContaDao;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class SimulandoBuscaMovimentacoesEager {

	public static void main(String[] args) {
		
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		
		Conta conta = new ContaDao(em).buscarContasEager(1L);
		System.out.println(conta.getCliente().getNome());
		
		em.getTransaction().commit();
		em.close();
		
	}
}
