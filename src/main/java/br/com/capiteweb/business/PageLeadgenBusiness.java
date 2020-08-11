package br.com.capiteweb.business;

import br.com.capiteweb.DAO.PageLeadgenDAO;
import br.com.capiteweb.model.PageLeadgen;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class PageLeadgenBusiness {
	private EntityManager em;
	private PageLeadgenDAO pageLeadgenDAO;

	public PageLeadgenBusiness(EntityManager em) {
		this.em = em;
		this.pageLeadgenDAO = new PageLeadgenDAO(em);
	}

	public PageLeadgen carregar(Long idPageLeadgen) {
		return this.pageLeadgenDAO.carregar(idPageLeadgen);
	}

	public PageLeadgen salvar(PageLeadgen pageLeadgen) {
		return this.pageLeadgenDAO.salvar(pageLeadgen);
	}

	public PageLeadgen buscaPorId(Long id) {
		return this.pageLeadgenDAO.buscarPorId(id);
	}
	
	public PageLeadgen buscaPorIdPage(String idPage) {
		return this.pageLeadgenDAO.buscarPorIdPage(idPage);
	}

	public List<PageLeadgen> buscaPorNome(Parametro parametro) {
		return this.pageLeadgenDAO.buscarPorNome(parametro);
	}

	public List<PageLeadgen> getListPorEmpresa(Long id) {
		return this.pageLeadgenDAO.getListPorEmpresa(id);
	}
	
	public List<PageLeadgen> getListPorEmpresaDisponivel(Long id) {
		return this.pageLeadgenDAO.getListPorEmpresaDisponivel(id);
	}

	public boolean excluir(long id) {
		return this.pageLeadgenDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}