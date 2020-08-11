package br.com.capiteweb.DAO;

import br.com.capiteweb.model.FormLeadgen;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class FormLeadgenDAO {
	EntityManager em;

	public FormLeadgenDAO(EntityManager em) {
		this.em = em;
	}

	public FormLeadgen salvar(FormLeadgen formLeadgen) {
		try {
			formLeadgen = (FormLeadgen) this.saveOrUpdate(formLeadgen);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return formLeadgen;
	}

	public boolean excluir(long id) {
		try {
			FormLeadgen formLeadgen = this.carregar(id);
			this.delete(formLeadgen);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public FormLeadgen buscarPorId(Long id) {
		String Sql = "select u from FormLeadgen u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<FormLeadgen> formLeadgen = consulta.getResultList();
		if (formLeadgen.size() == 0) {
			formLeadgen.add(new FormLeadgen());
		}

		return (FormLeadgen) formLeadgen.get(0);
	}
	
	public FormLeadgen buscarPorIdForm(String idForm) {
		String Sql = "select u from FormLeadgen u where u.idForm =:idForm ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("idForm", idForm);
		List<FormLeadgen> formLeadgen = consulta.getResultList();
		if (formLeadgen.size() == 0) {
			formLeadgen.add(new FormLeadgen());
		}

		return (FormLeadgen) formLeadgen.get(0);
	}

	public List<FormLeadgen> buscarPorNome(Parametro parametro) {
		String Sql = "select u from FormLeadgen u where (u.nomeForm like :nome or u.nomeForm is null ) and u.idEmpresa=:idEmpresa ";
		Query consulta = this.em.createQuery(Sql);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		List<FormLeadgen> lista = consulta.getResultList();
		return lista;
	}

	public List<FormLeadgen> getListPorEmpresa(Long id) {
		String sql = "select u from FormLeadgen u where u.idEmpresa = :id order by nomeForm";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<FormLeadgen> formLeadgenList = consulta.getResultList();
		return formLeadgenList;
	}
	
	public List<FormLeadgen> getListPorEmpresaDisponivel(Long id) {
		String sql = "select u from FormLeadgen u where u.idEmpresa = :id and u.situacao = 'Disponivel' order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<FormLeadgen> formLeadgenList = consulta.getResultList();
		return formLeadgenList;
	}

	public FormLeadgen carregar(Long id) {
		String sql = "select u from FormLeadgen u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<FormLeadgen> formLeadgen = consulta.getResultList();
		return (FormLeadgen) formLeadgen.get(0);
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
			if (((FormLeadgen) t).getId() == null) {
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