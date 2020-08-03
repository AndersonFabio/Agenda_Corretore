package br.com.capiteweb.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import br.com.capiteweb.model.Cep;

public class CepDAOHibernate {

	EntityManager em;

	public CepDAOHibernate(EntityManager em) {
		this.em = em;
	}

	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	public Cep getCep(String cep) {
		String sql = "select CAST(uf AS char CHARACTER SET utf8) as uf from uf where substr(:cep,1,5) between Cep1 and Cep2";
		Query query = em.createNativeQuery(sql);
		query.setParameter("cep", cep);
		List<Object> uf = (List<Object>) query.getResultList();

		sql = "select cidade, logradouro, bairro, tp_logradouro from " + ((String) uf.get(0)).toLowerCase()
				+ " where cep=:cep ";
		query = em.createNativeQuery(sql);
		query.setParameter("cep", cep);
		List<Object[]> result = (List<Object[]>) query.getResultList();
		Cep endereco = new Cep();
		if (result.size() > 0) {
			endereco = new Cep();
			endereco.setUF((String) uf.get(0));
			for (Object[] item : result) {
				endereco.setCidade((String) item[0]);
				endereco.setEndereco((String) item[3] + " " + (String) item[1]);
				endereco.setBairro((String) item[2]);
			}
		}
		return endereco;
	}

}
