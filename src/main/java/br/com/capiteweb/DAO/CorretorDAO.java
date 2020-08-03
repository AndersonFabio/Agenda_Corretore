package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Corretor;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Parametro;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class CorretorDAO {
	EntityManager em;

	public CorretorDAO(EntityManager em) {
		this.em = em;
	}

	public Corretor salvar(Corretor corretor) {
		try {
			corretor = this.saveOrUpdate(corretor);
		} catch (Exception var3) {
			var3.printStackTrace();
		}
		return corretor;

	}

	public boolean excluir(Long id) {
		try {
			Corretor corretor = this.carregar(id);
			this.delete(corretor);
			return true;
		} catch (Exception var3) {
			return false;
		}
	}

	public Corretor carregar(long l) {
		return (Corretor) this.em.find(Corretor.class, l);
	}

	public Corretor buscarPorLogin(Login login) {
		String sql = "select u from Corretor u where u.email =:email and u.senha =:senha ";
		TypedQuery<Corretor> consulta = this.em.createQuery(sql, Corretor.class);
		consulta.setParameter("email", login.getEmail());
		consulta.setParameter("senha", login.getSenha());
		List<Corretor> corretor = consulta.getResultList();
		if (corretor.size() == 0) {
			corretor.add(new Corretor());
		}

		return (Corretor) corretor.get(0);
	}

	public String buscarPorEmail(String email) {
		TypedQuery<Corretor> consulta = this.em.createQuery("select u from Corretor u where u.email =:email",
				Corretor.class);
		consulta.setParameter("email", email);
		List<Corretor> corretor = consulta.getResultList();
		return corretor.size() == 0 ? "" : ((Corretor) corretor.get(0)).getEmail();
	}

	public Corretor buscarCorretorPorEmail(String email) {
		TypedQuery<Corretor> consulta = this.em.createQuery("select u from Corretor u where u.email =:email",
				Corretor.class);
		consulta.setParameter("email", email);
		List<Corretor> corretor = consulta.getResultList();
		return (Corretor) corretor.get(0);
	}

	public Corretor carregar(Long codigo) {
		Corretor Corretor = (Corretor) this.em.find(Corretor.class, codigo);
		return Corretor;
	}

	public List<Corretor> getListCorretorPorIdEmpresa(Parametro parametro) {
		String sql = null;
		if(parametro.getLogin().getCargo().equals("Imobiliaria")) {
			sql = "select u from Corretor u where u.idEmpresa = :id order by nome";
		} else {
			sql = "select u from Corretor u where u.id = :id order by nome";
		}
		Query consulta = this.em.createQuery(sql);
		if(parametro.getLogin().getCargo().equals("Imobiliaria")) {
			consulta.setParameter("id", parametro.getLogin().getIdEmpresa());
		} else {
			consulta.setParameter("id", parametro.getLogin().getIdCorretor());
		}
		
		List<Corretor> corretorList = consulta.getResultList();
		return corretorList;
	}

	public void vincularCorretor(Long idCorretor, Long idEmpresa, Long idEmpresaNova) {
		String sql = "update empreendimento set idEmpresa=:idEmpresaNova where idEmpresa =:idEmpresa";
		Query query = this.em.createNativeQuery(sql);
		query.setParameter("idEmpresaNova", idEmpresaNova);
		query.setParameter("idEmpresa", idEmpresa);
		this.em.getTransaction().begin();
		query.executeUpdate();
		this.em.getTransaction().commit();
		sql = "update cliente set idEmpresa=:idEmpresaNova where idEmpresa=:idEmpresa and idCorretor=:idCorretor";
		query = this.em.createNativeQuery(sql);
		query.setParameter("idCorretor", idCorretor);
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idEmpresaNova", idEmpresaNova);
		this.em.getTransaction().begin();
		query.executeUpdate();
		this.em.getTransaction().commit();
		sql = "update captacao set idEmpresa=:idEmpresaNova where idEmpresa=:idEmpresa and idCorretor=:idCorretor";
		query = this.em.createNativeQuery(sql);
		query.setParameter("idCorretor", idCorretor);
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idEmpresaNova", idEmpresaNova);
		this.em.getTransaction().begin();
		query.executeUpdate();
		this.em.getTransaction().commit();
		sql = "update a01usuario set idEmpresa=:idEmpresaNova where idEmpresa=:idEmpresa and id=:idCorretor";
		query = this.em.createNativeQuery(sql);
		query.setParameter("idCorretor", idCorretor);
		query.setParameter("idEmpresa", idEmpresa);
		query.setParameter("idEmpresaNova", idEmpresaNova);
		this.em.getTransaction().begin();
		query.executeUpdate();
		this.em.getTransaction().commit();
	}

	public List<Corretor> getListSupervisorPorIdEmpresa(Long id) {
		String sql = "select u from Corretor u where u.idEmpresa = :id and u.cargo = :cargo order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		consulta.setParameter("cargo", "Supervisor");
		List<Corretor> corretorList = consulta.getResultList();
		return corretorList;
	}

	public List<Corretor> getListSupervisorPorIdSupervisor(Long id) {
		new ArrayList();
		List<Corretor> corretor = this.getListCorretorPorIdCorretor(id);
		String sql = "select u from Corretor u where u.idSupervisor = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		List<Corretor> corretorList = new ArrayList();
		if (((Corretor) corretor.get(0)).getIdSupervisor() != null) {
			consulta.setParameter("id", ((Corretor) corretor.get(0)).getIdSupervisor());
			corretorList.add((Corretor) consulta.getSingleResult());
		}

		return corretorList;
	}

	public List<Corretor> getListSupervisorPorIdCorretor(Long id) {
		new ArrayList();
		List<Corretor> corretor = this.getListCorretorPorIdCorretor(id);
		String sql = null;
		sql = "select u from Corretor u where u.id = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		List<Corretor> supervisorList = new ArrayList();
		if (((Corretor) corretor.get(0)).getIdSupervisor() != null) {
			consulta.setParameter("id", ((Corretor) corretor.get(0)).getIdSupervisor());
			supervisorList.add((Corretor) consulta.getSingleResult());
		}

		return supervisorList;
	}

	public List<Corretor> getListCorretorPorIdSupervisor(Long id) {
		String sql = "select u from Corretor u where (u.idSupervisor = :id or u.id =:id) order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Corretor> corretorList = consulta.getResultList();
		return corretorList;
	}

	public List<Corretor> getListCorretorPorIdCorretor(Long id) {
		String sql = "select u from Corretor u where u.id = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Corretor> corretorList = new ArrayList();
		corretorList.add((Corretor) consulta.getSingleResult());
		return corretorList;
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
		Corretor corretor = new Corretor();
		try {
			this.em.getTransaction().begin();
			if (((Corretor) t).getId() == null) {
				this.em.persist(t);
			} else {
				corretor = (Corretor) this.em.merge(t);
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