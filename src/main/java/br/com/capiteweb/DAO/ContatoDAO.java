package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Contato;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ContatoDAO {
	EntityManager em;

	public ContatoDAO(EntityManager em) {
		this.em = em;
	}

	public void salvar(Contato contato) {
		try {
			this.saveOrUpdate(contato);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

	}

	public boolean excluir(long id) {
		try {
			Contato contato = this.carregar(id);
			this.delete(contato);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Contato carregar(long l) {
		return (Contato) this.em.find(Contato.class, l);
	}

	public Contato buscarPorEmail(String email) {
		String Sql = "select u from Contato u where u.email =:email ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("email", email);
		List<Contato> contato = consulta.getResultList();
		if (contato.size() == 0) {
			contato.add(new Contato());
		}

		return (Contato) contato.get(0);
	}

	public Contato buscarPorId(Long id) {
		String Sql = "select u from Contato u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Contato> contato = consulta.getResultList();
		if (contato.size() == 0) {
			contato.add(new Contato());
		}

		return (Contato) contato.get(0);
	}

	public Contato buscarPorNome(String nome) {
		String Sql = "select u from Contato u where u.nome =:nome ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("nome", nome);
		List<Contato> contato = consulta.getResultList();
		if (contato.size() == 0) {
			contato.add(new Contato());
		}

		return (Contato) contato.get(0);
	}

	public Contato carregar(Long codigo) {
		Contato Contato = (Contato) this.em.find(Contato.class, codigo);
		return Contato;
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
			this.em.persist(t);
			this.em.getTransaction().commit();
		} catch (Exception var6) {
			throw var6;
		} finally {
			this.em.clear();
		}

		return t;
	}
}