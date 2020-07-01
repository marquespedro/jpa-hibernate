package br.com.financas.teste;

import javax.persistence.EntityManager;

import br.com.financas.dao.MovimentacoesDao;
import br.com.financas.modelo.Conta;
import br.com.financas.modelo.TipoMovimentacao;
import br.com.financas.util.JPAUtil;

public class TesteJPQLFuncoes {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();
		
		MovimentacoesDao dao = new MovimentacoesDao(em);
		dao.retornaSomaMovimentacoes(new Conta(3L));
		dao.retornaMediasMovimentacoesPorTipo(TipoMovimentacao.ENTRADA);
		dao.retornaMaiorValorMovimentacao(new Conta(2L));
		dao.retornaQuantidaedMovimentacoes(new Conta(2L));
		dao.retornaMediaPorDia(new Conta(2L), TipoMovimentacao.ENTRADA);
		
		em.close();

	}

}
