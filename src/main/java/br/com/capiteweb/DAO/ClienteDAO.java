package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Cliente;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class ClienteDAO {
	private EntityManager em;

	public ClienteDAO(EntityManager em) {
		this.em = em;
	}

	public Cliente salvar(Cliente cliente) {
		try {
			cliente = (Cliente) this.saveOrUpdate(cliente);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return cliente;
	}

	public boolean excluir(long id) {
		try {
			Cliente cliente = this.carregar(id);
			this.delete(cliente);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Cliente buscarPorId(Long id) {
		String Sql = "select u from Cliente u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Cliente> cliente = consulta.getResultList();
		if (cliente.size() == 0) {
			cliente.add(new Cliente());
		}

		return (Cliente) cliente.get(0);
	}

	public List<Cliente> buscarPorNomeSituacao(Parametro parametro) {
		String sql = null;
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			sql = "select u from Cliente u where u.nome like :nome and u.idEmpresa =:idEmpresa and u.idSituacao=:idSituacao order by u.nome";
		}

		if (parametro.getLogin().getCargo().equals("Supervisor")) {
			sql = "select u from Cliente u left outer join Corretor corr on u.idCorretor = corr.id where (corr.idSupervisor=:idSupervisor or corr.id=:idSupervisor ) and u.nome like :nome and u.idSituacao=:idSituacao order by u.nome ";
		}

		if (parametro.getLogin().getCargo().equals("Corretor")) {
			sql = "select u from Cliente u where u.nome like :nome and u.idCorretor =:idCorretor and u.idSituacao=:idSituacao order by u.nome";
		}

		Query consulta = this.em.createQuery(sql, Cliente.class);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		}

		if (parametro.getLogin().getCargo().equals("Supervisor")) {
			consulta.setParameter("idSupervisor", parametro.getLogin().getIdCorretor());
		}

		if (parametro.getLogin().getCargo().equals("Corretor")) {
			consulta.setParameter("idCorretor", parametro.getLogin().getIdCorretor());
		}
		
		consulta.setParameter("idSituacao", parametro.getIdSituacao());

		List<Cliente> lista = consulta.getResultList();
		return lista;
	}
	
	public List<Cliente> buscarPorNome(Parametro parametro) {
		String sql = null;
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			sql = "select u from Cliente u where u.nome like :nome and u.idEmpresa =:idEmpresa and u.idSituacao =:idSituacao order by u.nome";
		}

		if (parametro.getLogin().getCargo().equals("Supervisor")) {
			sql = "select u from Cliente u left outer join Corretor corr on u.idCorretor = corr.id where (corr.idSupervisor=:idSupervisor or corr.id=:idSupervisor ) and u.nome like :nome  and u.idSituacao =:idSituacao order by u.nome ";
		}

		if (parametro.getLogin().getCargo().equals("Corretor")) {
			sql = "select u from Cliente u where u.nome like :nome and u.idCorretor =:idCorretor  and u.idSituacao =:idSituacao  order by u.nome";
		}

		Query consulta = this.em.createQuery(sql, Cliente.class);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		}

		if (parametro.getLogin().getCargo().equals("Supervisor")) {
			consulta.setParameter("idSupervisor", parametro.getLogin().getIdCorretor());
		}

		if (parametro.getLogin().getCargo().equals("Corretor")) {
			consulta.setParameter("idCorretor", parametro.getLogin().getIdCorretor());
		}
		
		consulta.setParameter("idSituacao", parametro.getIdSituacao());
		
		List<Cliente> lista = consulta.getResultList();
		return lista;
	}


	public List<Cliente> getListPorEmpresa(Long id) {
		String sql = "select u from Cliente u where u.idEmpresa = :id order by nome";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Cliente> clienteList = consulta.getResultList();
		return clienteList;
	}

	public Cliente carregar(Long id) {
		String sql = "select u from Cliente u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Cliente> cliente = (List<Cliente>) consulta.getResultList();
		if(cliente.size() == 0) {
			cliente.add(new Cliente());
		}
		return cliente.get(0);
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
			if (((Cliente) t).getId() == null) {
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