package br.com.capiteweb.DAO;

import br.com.capiteweb.model.PageLeadgen;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class PageLeadgenDAO {
	EntityManager em;

	public PageLeadgenDAO(EntityManager em) {
		this.em = em;
	}

	public PageLeadgen salvar(PageLeadgen pageLeadgen) {
		try {
			pageLeadgen = (PageLeadgen) this.saveOrUpdate(pageLeadgen);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return pageLeadgen;
	}

	public boolean excluir(long id) {
		try {
			PageLeadgen pageLeadgen = this.carregar(id);
			this.delete(pageLeadgen);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public PageLeadgen buscarPorId(Long id) {
		String Sql = "select u from PageLeadgen u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<PageLeadgen> pageLeadgen = consulta.getResultList();
		if (pageLeadgen.size() == 0) {
			pageLeadgen.add(new PageLeadgen());
		}

		return (PageLeadgen) pageLeadgen.get(0);
	}
	
	public PageLeadgen buscarPorIdPage(String idPage) {
		String Sql = "select u from PageLeadgen u where u.idPage =:idPage ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("idPage", idPage);
		List<PageLeadgen> pageLeadgen = consulta.getResultList();
		if (pageLeadgen.size() == 0) {
			pageLeadgen.add(new PageLeadgen());
		}

		return (PageLeadgen) pageLeadgen.get(0);
	}

	public List<PageLeadgen> buscarPorNome(Parametro parametro) {
		String Sql = "select u from PageLeadgen u where u.nome like :nome and u.idEmpresa=:idEmpresa ";
		Query consulta = this.em.createQuery(Sql);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		List<PageLeadgen> lista = consulta.getResultList();
		return lista;
	}

	public List<PageLeadgen> getListPorEmpresa(Long id) {
		String sql = "select u from PageLeadgen u where u.idEmpresa = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<PageLeadgen> pageLeadgenList = consulta.getResultList();
		return pageLeadgenList;
	}
	
	public List<PageLeadgen> getListPorEmpresaDisponivel(Long id) {
		String sql = "select u from PageLeadgen u where u.idEmpresa = :id and u.situacao = 'Disponivel' order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<PageLeadgen> pageLeadgenList = consulta.getResultList();
		return pageLeadgenList;
	}

	public PageLeadgen carregar(Long id) {
		String sql = "select u from PageLeadgen u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<PageLeadgen> pageLeadgen = consulta.getResultList();
		return (PageLeadgen) pageLeadgen.get(0);
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
			if (((PageLeadgen) t).getId() == null) {
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