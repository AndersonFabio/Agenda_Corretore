package br.com.capiteweb.DAO;

import br.com.capiteweb.model.CheckoutPagamento;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

public class CheckoutPagamentoDAO {
	EntityManager em;

	public CheckoutPagamentoDAO(EntityManager em) {
		this.em = em;
	}

	public CheckoutPagamento salvar(CheckoutPagamento checkoutPagamento) {
		try {
			checkoutPagamento = (CheckoutPagamento) this.saveOrUpdate(checkoutPagamento);
		} catch (Exception var3) {
			var3.printStackTrace();
		}

		return checkoutPagamento;
	}

	public boolean excluir(long id) {
		try {
			CheckoutPagamento checkoutPagamento = this.carregar(id);
			this.delete(checkoutPagamento);
			return true;
		} catch (Exception var4) {
			return false;
		}
	}

	public CheckoutPagamento buscarPorId(Long id) {
		String Sql = "select u from CheckoutPagamento u where u.id =:id ";
		Query consulta = this.em.createQuery(Sql);
		consulta.setParameter("id", id);
		List<CheckoutPagamento> checkoutPagamento = consulta.getResultList();
		if (checkoutPagamento.size() == 0) {
			checkoutPagamento.add(new CheckoutPagamento());
		}

		return (CheckoutPagamento) checkoutPagamento.get(0);
	}

	public CheckoutPagamento carregar(Long id) {
		String sql = "select u from CheckoutPagamento u where u.id = :id";
		Query consulta = this.em.createQuery(sql);
		consulta.setParameter("id", id);
		List<CheckoutPagamento> checkoutPagamento = consulta.getResultList();
		return (CheckoutPagamento) checkoutPagamento.get(0);
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
			if (((CheckoutPagamento) t).getId() == null) {
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