package br.com.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.financas.dao.MovimentacoesDao;
import br.com.financas.dto.MovimentacaoDTO;
import br.com.financas.util.JPAUtil;

public class TesteJPQLJoinWithDTO {

	public static void main(String[] args) {

		EntityManager em = JPAUtil.getEntityManager();

		List<MovimentacaoDTO> resultado =  new MovimentacoesDao(em).retornaConsultaComoDTO(1L);

		resultado.stream().forEach(t -> {
			System.out.println(t.toString());
		});
		
		em.close();

	}

}
