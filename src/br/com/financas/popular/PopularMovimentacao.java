package br.com.financas.popular;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.financas.dao.CategoriaDao;
import br.com.financas.dao.ContaDao;
import br.com.financas.modelo.Categoria;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.Movimentacao;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class PopularMovimentacao {

	public static void main(String[] args) {


		EntityManager em = JPAUtil.getEntityManager();
	
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ContaDao contaDao = new ContaDao(em);
		
		List<Categoria> categorias = categoriaDao.buscarCategorias();
		Conta conta = contaDao.buscarContasEager(1L);
		
		Movimentacao m1 = 
				new Movimentacao(Date.from(Instant.now()), 
						TipoMovimentacao.SAIDA, new BigDecimal(200), conta, new HashSet<Categoria>(Arrays.asList(categorias.get(0), categorias.get(3))));
	
		Movimentacao m2 = 
				new Movimentacao(Date.from(Instant.now()), 
						TipoMovimentacao.ENTRADA, new BigDecimal(200), conta, new HashSet<Categoria>(Arrays.asList(categorias.get(1), categorias.get(2))));
		
		Movimentacao m3 = 
				new Movimentacao(Date.from(Instant.now()), 
						TipoMovimentacao.SAIDA, new BigDecimal(200), conta, new HashSet<Categoria>(Arrays.asList(categorias.get(4), categorias.get(3))));
		
		Movimentacao m4 = 
				new Movimentacao(Date.from(Instant.now()), 
						TipoMovimentacao.ENTRADA, new BigDecimal(200), conta, new HashSet<Categoria>(Arrays.asList(categorias.get(2), categorias.get(3))));
		
		List<Movimentacao> movimentacoes = Arrays.asList(m1,m2,m3,m4);
		
		em.getTransaction().begin();
		
		movimentacoes.forEach(m -> {
			em.persist(m);
		});
		
		em.getTransaction().commit();

		em.close();
	}
}
