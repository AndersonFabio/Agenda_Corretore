package br.com.capiteweb.business;

import br.com.capiteweb.DAO.SituacaoDAO;
import br.com.capiteweb.model.Situacao;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class SituacaoBusiness {
	private EntityManager em;
	private SituacaoDAO situacaoDAO;

	public SituacaoBusiness(EntityManager em) {
		this.em = em;
		this.situacaoDAO = new SituacaoDAO(em);
	}

	public Situacao carregar(Long idSituacao) {
		return this.situacaoDAO.carregar(idSituacao);
	}

	public Situacao salvar(Situacao situacao) {
		return this.situacaoDAO.salvar(situacao);
	}

	public Situacao buscaPorId(Long id) {
		return this.situacaoDAO.buscarPorId(id);
	}

	public List<Situacao> buscaPorNome(Parametro parametro) {
		return this.situacaoDAO.buscarPorNome(parametro);
	}

	public List<Situacao> getListPorEmpresa(Long id) {
		return this.situacaoDAO.getListPorEmpresa(id);
	}
	
	public boolean excluir(long id) {
		return this.situacaoDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}