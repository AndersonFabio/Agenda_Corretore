package br.com.capiteweb.business;

import br.com.capiteweb.DAO.FormLeadgenDAO;
import br.com.capiteweb.model.FormLeadgen;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class FormLeadgenBusiness {
	private EntityManager em;
	private FormLeadgenDAO formLeadgenDAO;

	public FormLeadgenBusiness(EntityManager em) {
		this.em = em;
		this.formLeadgenDAO = new FormLeadgenDAO(em);
	}

	public FormLeadgen carregar(Long idFormLeadgen) {
		return this.formLeadgenDAO.carregar(idFormLeadgen);
	}

	public FormLeadgen salvar(FormLeadgen formLeadgen) {
		return this.formLeadgenDAO.salvar(formLeadgen);
	}

	public FormLeadgen buscaPorId(Long id) {
		return this.formLeadgenDAO.buscarPorId(id);
	}
	
	public FormLeadgen buscaPorIdForm(String idForm) {
		return this.formLeadgenDAO.buscarPorIdForm(idForm);
	}

	public List<FormLeadgen> buscaPorNome(Parametro parametro) {
		return this.formLeadgenDAO.buscarPorNome(parametro);
	}

	public List<FormLeadgen> getListPorEmpresa(Long id) {
		return this.formLeadgenDAO.getListPorEmpresa(id);
	}
	
	public List<FormLeadgen> getListPorEmpresaDisponivel(Long id) {
		return this.formLeadgenDAO.getListPorEmpresaDisponivel(id);
	}

	public boolean excluir(long id) {
		return this.formLeadgenDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}