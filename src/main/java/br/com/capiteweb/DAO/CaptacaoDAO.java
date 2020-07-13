package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Captacao;
import br.com.capiteweb.model.Cliente;
import br.com.capiteweb.model.Parametro;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CaptacaoDAO {
	EntityManager em;

	public CaptacaoDAO(EntityManager em) {
		this.em = em;
	}

	public Captacao salvar(Captacao captacao) {
		try {
			captacao = (Captacao) this.saveOrUpdate(captacao);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return captacao;
	}

	public boolean excluir(long id) {
		try {
			Captacao captacao = this.carregar(id);
			this.delete(captacao);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public Captacao buscarPorId(Parametro parametro) {
		String sql = null;
		Long id = null;
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			sql = "select u from Captacao u where u.idEmpresa =:id ";
			id = parametro.getLogin().getIdEmpresa();
		} else {
			sql = "select u from Captacao u where u.idCorretor =:id ";
			id = parametro.getLogin().getIdCorretor();
		}

		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Captacao> captacao = consulta.getResultList();
		if (captacao.size() == 0) {
			Captacao captacaoNova = new Captacao();
			captacaoNova.setData1(new Date());
			captacaoNova.setTitulo1("");
			captacaoNova.setAnuncio1("");
			captacaoNova.setSituacao1("A Pagar");
			captacao.add(captacaoNova);
		}

		return (Captacao) captacao.get(0);
	}
	
	public List<Captacao> buscarPorNome(Parametro parametro) {
		String sql = null;
		if (parametro.getLogin().getCargo().equals("Imobiliaria")) {
			sql = "select u from Captacao u where u.titulo1 like :nome and u.idEmpresa =:idEmpresa ";
		}

		if (parametro.getLogin().getCargo().equals("Supervisor")) {
			sql = "select u from Captacao u left outer join Corretor corr on u.idCorretor = corr.id where (corr.idSupervisor=:idSupervisor or corr.id=:idSupervisor ) and u.titulo1 like :nome ";
		}

		if (parametro.getLogin().getCargo().equals("Corretor")) {
			sql = "select u from Captacao u where u.titulo1 like :nome and u.idCorretor =:idCorretor ";
		}

		Query consulta = this.em.createQuery(sql, Captacao.class);
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

		List<Captacao> lista = consulta.getResultList();
		return lista;
	}

	public Captacao carregar(Long id) {
		String sql = "select u from Captacao u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<Captacao> captacao = consulta.getResultList();
		return (Captacao) captacao.get(0);
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
			if (((Captacao) t).getId() == null) {
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