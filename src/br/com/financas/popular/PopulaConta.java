package br.com.financas.popular;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.financas.modelo.Cliente;
import br.com.financas.modelo.Conta;
import br.com.financas.util.JPAUtil;

public class PopulaConta {

	public static void main(String[] args) {

		EntityManager manager =  JPAUtil.getEntityManager();

		manager.getTransaction().begin();
	
		Cliente c1 = new Cliente("Pedro Ivo", "00000000001", "QC 14 Rua M1");
		Cliente c2 = new Cliente("Roza", "00000000002", "QC 14 Rua M2");
		Cliente c3 = new Cliente("Kel", "00000000003", "QC 14 Rua M3");
		Cliente c4 = new Cliente("Nay", "00000000004", "QC 14 Rua M4");		
		Cliente c5 = new Cliente("JOAO", "00000000005", "QC 14 Rua M4");

		List<Cliente> clientes = Arrays.asList(c1,c2,c3,c4,c5);
		
	
		clientes.forEach(cliente -> {
			manager.persist(cliente);
		});
		
		
		Conta conta1 = new Conta();
		Conta conta2 = new Conta();
		Conta conta3 = new Conta();
		Conta conta4 = new Conta();
		Conta conta5 = new Conta();

		conta1.setBanco("001 - BANCO DO BRASIL");
		conta1.setNumero("16987-8");
		conta1.setAgencia("6543");
		conta1.setCliente(c1);

		conta2.setBanco("237 - BANCO BRADESCO");
		conta2.setNumero("86759-1");
		conta2.setAgencia("1745");
		conta2.setCliente(c2);

		conta3.setBanco("341 - BANCO ITAU UNIBANCO");
		conta3.setNumero("46346-3");
		conta3.setAgencia("4606");
		conta3.setCliente(c3);

		conta4.setBanco("033 - BANCO SANTANDER");
		conta4.setNumero("12345-6");
		conta4.setAgencia("9876");
		conta4.setCliente(c4);

		conta5.setBanco("104 - CAIXA ECONOMICA FEDERAL");
		conta5.setNumero("98654-3");
		conta5.setAgencia("1234");
		conta5.setCliente(c5);

		// persistindo as contas
		manager.persist(conta1);
		manager.persist(conta2);
		manager.persist(conta3);
		manager.persist(conta4);
		manager.persist(conta5);

		manager.getTransaction().commit();

		manager.close();

	}
}
