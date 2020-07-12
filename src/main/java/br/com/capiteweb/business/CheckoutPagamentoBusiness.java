package br.com.capiteweb.business;

import br.com.capiteweb.DAO.CheckoutPagamentoDAO;
import br.com.capiteweb.model.CheckoutPagamento;
import javax.persistence.EntityManager;

public class CheckoutPagamentoBusiness {
	private EntityManager em;
	private CheckoutPagamentoDAO checkoutPagamentoDAO;

	public CheckoutPagamentoBusiness(EntityManager em) {
		this.em = em;
		this.checkoutPagamentoDAO = new CheckoutPagamentoDAO(em);
	}

	public CheckoutPagamento carregar(Long idCheckoutPagamento) {
		return this.checkoutPagamentoDAO.carregar(idCheckoutPagamento);
	}

	public CheckoutPagamento salvar(CheckoutPagamento checkoutPagamento) {
		return this.checkoutPagamentoDAO.salvar(checkoutPagamento);
	}

	public CheckoutPagamento buscaPorId(Long id) {
		return this.checkoutPagamentoDAO.buscarPorId(id);
	}

	public boolean excluir(long id) {
		return this.checkoutPagamentoDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}