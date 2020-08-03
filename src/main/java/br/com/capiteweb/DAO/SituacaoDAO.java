package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Situacao;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class SituacaoDAO {
	EntityManager em;

	public SituacaoDAO(EntityManager em) {
		this.em = em;
	}

	public Situacao salvar(Situacao situacao) {
		try {
			situacao = (Situacao) this.saveOrUpdate(situacao);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return situacao;
	}

	public boolean excluir(long id) {
		try {
			Situacao situacao = this.carregar(id);
			this.delete(situacao);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Situacao buscarPorId(Long id) {
		String Sql = "select u from Situacao u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Situacao> situacao = consulta.getResultList();
		if (situacao.size() == 0) {
			situacao.add(new Situacao());
		}

		return (Situacao) situacao.get(0);
	}

	public List<Situacao> buscarPorNome(Parametro parametro) {
		String Sql = "select u from Situacao u where u.nome like :nome and u.idEmpresa=:idEmpresa ";
		Query consulta = this.em.createQuery(Sql);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		List<Situacao> lista = consulta.getResultList();
		return lista;
	}

	public List<Situacao> getListPorEmpresa(Long id) {
		String sql = "select u from Situacao u where u.idEmpresa = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Situacao> situacaoList = consulta.getResultList();
		return situacaoList;
	}
	

	public Situacao carregar(Long id) {
		String sql = "select u from Situacao u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Situacao> situacao = consulta.getResultList();
		return (Situacao) situacao.get(0);
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
			if (((Situacao) t).getId() == null) {
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