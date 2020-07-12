package br.com.capiteweb.commons;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HibernateUtil {
	protected static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu-capteweb");
	protected static EntityManager entityManager;
	protected static EntityTransaction transaction;

	static {
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager2() {
		EntityManager entityManager2 = entityManagerFactory.createEntityManager();
		return entityManager2;
	}

	public static EntityManager getEntityManager() {
		return entityManager;
	}

	public static EntityTransaction getTransaction() {
		return transaction;
	}
}