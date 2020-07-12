package br.com.capiteweb.DAO;

import br.com.capiteweb.model.Captacao;
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