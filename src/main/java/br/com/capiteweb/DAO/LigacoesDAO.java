package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Ligacoes;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class LigacoesDAO {
	EntityManager em;

	public LigacoesDAO(EntityManager em) {
		this.em = em;
	}

	public Ligacoes salvar(Ligacoes ligacoes) {
		try {
			ligacoes = (Ligacoes) this.saveOrUpdate(ligacoes);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return ligacoes;
	}

	public boolean excluir(long id) {
		try {
			Ligacoes ligacoes = this.carregar(id);
			this.delete(ligacoes);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public List<Ligacoes> buscarPorIdAgenda(Long id) {
		String Sql = "select u from Ligacoes u where u.idAgenda =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Ligacoes> ligacoes = consulta.getResultList();
		if (ligacoes.size() == 0) {
			ligacoes.add(new Ligacoes());
		}

		return ligacoes;
	}

	public Ligacoes carregar(Long id) {
		String sql = "select u from Ligacoes u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Ligacoes> ligacoes = consulta.getResultList();
		return (Ligacoes) ligacoes.get(0);
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
			if (((Ligacoes) t).getId() == null) {
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