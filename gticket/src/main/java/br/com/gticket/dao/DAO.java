package br.com.gticket.dao;

import javax.persistence.EntityManager;

import br.com.gticket.util.JpaUtil;

public abstract class DAO {

	EntityManager getEntityManager() {
		EntityManager entityManager = JpaUtil.getEntityManager();
		return entityManager;
	}
}