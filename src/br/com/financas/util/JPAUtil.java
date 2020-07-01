package br.com.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("curso-jpa");

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}
