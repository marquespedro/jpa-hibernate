package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.dao.ContaDao;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class TesteMetodoBuscarContaMovimentacoesCriteria {
	
	public static void main(String[] args) {
		
		EntityManager em = JPAUtil.getEntityManager();

		
		Conta conta  = new ContaDao(em).buscarContaMovimentacoesCriteria(1L);
			
		conta.getMovimentacoes().forEach(m -> System.out.println(m.toString()));
		conta.getMovimentacoes().forEach(m -> {
			m.getCategorias().forEach(c -> System.out.println(c.toString()));
		});
		
		System.out.println(conta.getCliente().getNome());
		
		
		
		em.close();
		
	}

	
	
	
}
