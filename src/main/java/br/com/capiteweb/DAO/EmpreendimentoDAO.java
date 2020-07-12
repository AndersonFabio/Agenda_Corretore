package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Empreendimento;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class EmpreendimentoDAO {
	EntityManager em;

	public EmpreendimentoDAO(EntityManager em) {
		this.em = em;
	}

	public Empreendimento salvar(Empreendimento empreendimento) {
		try {
			empreendimento = (Empreendimento) this.saveOrUpdate(empreendimento);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return empreendimento;
	}

	public boolean excluir(long id) {
		try {
			Empreendimento empreendimento = this.carregar(id);
			this.delete(empreendimento);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Empreendimento buscarPorId(Long id) {
		String Sql = "select u from Empreendimento u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Empreendimento> empreendimento = consulta.getResultList();
		if (empreendimento.size() == 0) {
			empreendimento.add(new Empreendimento());
		}

		return (Empreendimento) empreendimento.get(0);
	}

	public List<Empreendimento> buscarPorNome(Parametro parametro) {
		String Sql = "select u from Empreendimento u where u.nome like :nome and u.idEmpresa=:idEmpresa ";
		Query consulta = this.em.createQuery(Sql);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		List<Empreendimento> lista = consulta.getResultList();
		return lista;
	}

	public List<Empreendimento> getListPorEmpresa(Long id) {
		String sql = "select u from Empreendimento u where u.idEmpresa = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Empreendimento> empreendimentoList = consulta.getResultList();
		return empreendimentoList;
	}

	public Empreendimento carregar(Long id) {
		String sql = "select u from Empreendimento u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Empreendimento> empreendimento = consulta.getResultList();
		return (Empreendimento) empreendimento.get(0);
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
			if (((Empreendimento) t).getId() == null) {
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