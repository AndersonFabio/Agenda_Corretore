package br.com.capiteweb.business;

import javax.persistence.EntityManager;

import br.com.capiteweb.DAO.CepDAOHibernate;
import br.com.capiteweb.model.Cep;


public class CepBusiness {

	
	private EntityManager em;
	
	private CepDAOHibernate cepDAO;

	public CepBusiness(EntityManager em) {
		this.em = em;
		this.cepDAO = new CepDAOHibernate(em);
	}
	
	
	public Cep getCep(String cep) {
		return cepDAO.getCep(cep);
	}


	public EntityManager getEm() {
		return em;
	}


	public void setEm(EntityManager em) {
		this.em = em;
	}


	public CepDAOHibernate getCepDAO() {
		return cepDAO;
	}


	public void setCepDAO(CepDAOHibernate cepDAO) {
		this.cepDAO = cepDAO;
	}
}
