package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Login;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EmpresaDAO {
	private EntityManager em;

	public EmpresaDAO(EntityManager em) {
		this.em = em;
	}

	public Empresa salvar(Empresa empresa) {
		try {
			empresa = (Empresa) this.saveOrUpdate(empresa);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return empresa;
	}

	public Empresa buscarPorLogin(Login login) {
		String Sql = "select u from Empresa u where u.email =:email and u.senha =:senha ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("email", login.getEmail());
		consulta.setParameter("senha", login.getSenha());
		List<Empresa> empresa = consulta.getResultList();
		if (empresa.size() == 0) {
			empresa.add(new Empresa());
		}

		return (Empresa) empresa.get(0);
	}

	public boolean excluir(long id) {
		try {
			Empresa empresa = this.carregar(id);
			this.delete(empresa);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Empresa carregar(long l) {
		return (Empresa) this.em.find(Empresa.class, l);
	}

	public String buscarPorEmail(String email) {
		String Sql = "select u from Empresa u where u.email =:email ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("email", email);
		List<Empresa> empresa = consulta.getResultList();
		if (empresa.size() == 0) {
			empresa.add(new Empresa());
		}

		return ((Empresa) empresa.get(0)).getEmail();
	}

	public Empresa buscarPorId(Long id) {
		String sql = "select u from Empresa u where u.id =:id ";
		TypedQuery<Empresa> consulta = this.em.createQuery(sql, Empresa.class);
		consulta.setParameter("id", id);
		List<Empresa> empresa = consulta.getResultList();
		if (empresa.size() == 0) {
			empresa.add(new Empresa());
		}

		return (Empresa) empresa.get(0);
	}

	public Empresa buscarPorNome(String nome) {
		String Sql = "select u from Empresa u where u.nome =:nome ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("nome", nome);
		List<Empresa> empresa = consulta.getResultList();
		if (empresa.size() == 0) {
			empresa.add(new Empresa());
		}

		return (Empresa) empresa.get(0);
	}

	public List<Empresa> getListAll() {
		List<Empresa> empresaList = null;
		if (empresaList == null) {
			Empresa empresa = new Empresa();
			empresa.setNome("Cadastrar");
			((List) empresaList).add(empresa);
		}

		return (List) empresaList;
	}

	public Empresa carregar(Long codigo) {
		Empresa Empresa = (Empresa) this.em.find(Empresa.class, codigo);
		return Empresa;
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
			if (((Empresa) t).getId() == null) {
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