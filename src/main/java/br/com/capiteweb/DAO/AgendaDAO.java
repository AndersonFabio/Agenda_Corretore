package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Agenda;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AgendaDAO {
	EntityManager em;

	public AgendaDAO(EntityManager em) {
		this.em = em;
	}

	public Agenda salvar(Agenda agenda) {
		try {
			agenda = (Agenda) this.saveOrUpdate(agenda);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return agenda;
	}

	public boolean excluir(long id) {
		try {
			Agenda agenda = this.carregar(id);
			this.delete(agenda);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Agenda buscarPorId(Long id) {
		String Sql = "select u from Agenda u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<Agenda> agenda = consulta.getResultList();
		if (agenda.size() == 0) {
			agenda.add(new Agenda());
		}

		return (Agenda) agenda.get(0);
	}

	public List<Agenda> buscarPorCorretor(Parametro parametro) {
		String sql = null;
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			sql = "select u from Agenda u where u.idEmpresa=:idEmpresa and u.cliente.situacao <> 'Descartado' and u.cliente.nome like :nome order by data  ";
		} else if (parametro.getLogin().getCargo().equals("Supervisor")) {
			sql = "select u from Agenda u left outer join Corretor corr on u.cliente.idCorretor = corr.id where (corr.idSupervisor = :idCorretor or corr.id=:idCorretor) and u.cliente.situacao <> 'Descartado' and u.cliente.nome like :nome order by data  ";
		} else {
			sql = "select u from Agenda u where u.cliente.idCorretor=:idCorretor and u.cliente.situacao <> 'Descartado' and u.cliente.nome like :nome order by data  ";
		}

		Query consulta = this.em.createQuery(sql);
		if (parametro.getPesquisar() == null) {
			parametro.setPesquisar("");
		}

		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			consulta.setParameter("idEmpresa", parametro.getLogin().getIdEmpresa());
		} else {
			consulta.setParameter("idCorretor", parametro.getLogin().getIdCorretor());
		}

		consulta.setParameter("nome", "%" + parametro.getPesquisar() + "%");
		List<Agenda> lista = consulta.getResultList();
		return lista;
	}

	public Agenda carregar(Long id) {
		String sql = "select u from Agenda u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Agenda> agenda = consulta.getResultList();
		return (Agenda) agenda.get(0);
	}
	
	public Agenda carregarPorCliente(Long idCliente) {
		String sql = "select u from Agenda u where u.idCliente = :idCliente";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("idCliente", idCliente);
		List<Agenda> agenda = consulta.getResultList();
		if(agenda.size() == 0) {
			return new Agenda();
		} else {
			return (Agenda) agenda.get(0);
		}
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
			if (((Agenda) t).getId() == null) {
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