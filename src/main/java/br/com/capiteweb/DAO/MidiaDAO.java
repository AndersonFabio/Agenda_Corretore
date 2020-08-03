package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Midia;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class MidiaDAO {
	EntityManager em;

	public MidiaDAO(EntityManager em) {
		this.em = em;
	}

	public Midia salvar(Midia midia) {
		try {
			midia = (Midia) this.saveOrUpdate(midia);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return midia;
	}

	public boolean excluir(long id) {
		try {
			Midia midia = this.carregar(id);
			this.delete(midia);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Midia buscarPorId(Long id) {
		String Sql = "select u from Midia u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Midia> midia = consulta.getResultList();
		if (midia.size() == 0) {
			midia.add(new Midia());
		}

		return (Midia) midia.get(0);
	}

	public List<Midia> buscarPorNome(Parametro parametro) {
		String Sql = "select u from Midia u where u.nome like :nome and u.idEmpresa=:idEmpresa ";
		Query consulta = this.em.createQuery(Sql);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		List<Midia> lista = consulta.getResultList();
		return lista;
	}

	public List<Midia> getListPorEmpresa(Long id) {
		String sql = "select u from Midia u where u.idEmpresa = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Midia> midiaList = consulta.getResultList();
		return midiaList;
	}
	

	public Midia carregar(Long id) {
		String sql = "select u from Midia u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Midia> midia = consulta.getResultList();
		return (Midia) midia.get(0);
	}

	public <T> T delete(T t) throws Exception {
		try {
			this.em.getTransaction().begin();
			this.em.remove(t);
			this.em.getTransaction().commit();
		} catch (Exception var6) {
			throw var6;
		} finally {
			this.em.clear();
		}

		return t;
	}

	public <T> T saveOrUpdate(T t) throws Exception {
		try {
			this.em.getTransaction().begin();
			if (((Midia) t).getId() == null) {
				this.em.persist(t);
			} else {
				this.em.merge(t);
			}

			this.em.getTransaction().commit();
		} catch (Exception var6) {
			throw var6;
		} finally {
			this.em.clear();
		}

		return t;
	}
}