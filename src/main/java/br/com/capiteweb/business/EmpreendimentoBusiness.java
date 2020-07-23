package br.com.capiteweb.business;

import br.com.capiteweb.DAO.EmpreendimentoDAO;
import br.com.capiteweb.model.Empreendimento;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class EmpreendimentoBusiness {
	private EntityManager em;
	private EmpreendimentoDAO empreendimentoDAO;

	public EmpreendimentoBusiness(EntityManager em) {
		this.em = em;
		this.empreendimentoDAO = new EmpreendimentoDAO(em);
	}

	public Empreendimento carregar(Long idEmpreendimento) {
		return this.empreendimentoDAO.carregar(idEmpreendimento);
	}

	public Empreendimento salvar(Empreendimento empreendimento) {
		return this.empreendimentoDAO.salvar(empreendimento);
	}

	public Empreendimento buscaPorId(Long id) {
		return this.empreendimentoDAO.buscarPorId(id);
	}

	public List<Empreendimento> buscaPorNome(Parametro parametro) {
		return this.empreendimentoDAO.buscarPorNome(parametro);
	}

	public List<Empreendimento> getListPorEmpresa(Long id) {
		return this.empreendimentoDAO.getListPorEmpresa(id);
	}
	
	public List<Empreendimento> getListPorEmpresaDisponivel(Long id) {
		return this.empreendimentoDAO.getListPorEmpresaDisponivel(id);
	}

	public boolean excluir(long id) {
		return this.empreendimentoDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}