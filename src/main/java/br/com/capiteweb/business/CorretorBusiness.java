package br.com.capiteweb.business;

import br.com.capiteweb.DAO.CorretorDAO;
import br.com.capiteweb.DAO.EmpresaDAO;
import br.com.capiteweb.model.Corretor;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Parametro;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class CorretorBusiness {
	private EntityManager em;
	private CorretorDAO corretorDAO;
	private EmpresaDAO empresaDAO;

	public CorretorBusiness(EntityManager em) {
		this.em = em;
		this.corretorDAO = new CorretorDAO(em);
		this.empresaDAO = new EmpresaDAO(em);
	}

	public Corretor carregar(Long idCorretor) {
		return this.corretorDAO.carregar(idCorretor);
	}

	public void salvar(Corretor corretor) {
		if (corretor.getIdEmpresa() == null) {
			Empresa empresa = new Empresa();
			empresa.setNome(corretor.getNome());
			empresa = this.empresaDAO.salvar(empresa);
			corretor.setIdEmpresa(empresa.getId());
		}

		this.corretorDAO.salvar(corretor);
	}

	public Login checkLogin(Login login) {
		Corretor usuario = this.buscaPorLogin(login);
		if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
			if ((new Date()).after(usuario.getVencimento())) {
				login.setAcesso("N");
			} else {
				login.setAcesso("S");
				login.setIdEmpresa(usuario.getIdEmpresa());
				login.setNome(usuario.getNome());
				login.setCargo(usuario.getCargo());
				login.setIdCorretor(usuario.getId());
			}
		}

		return login;
	}

	public Login checkLogin(Parametro parametro) {
		new Login();
		Login login = parametro.getLogin();
		Corretor usuario = this.buscaPorLogin(login);
		if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
			if ((new Date()).after(usuario.getVencimento())) {
				login.setAcesso("N");
			} else {
				login.setAcesso("S");
				login.setIdEmpresa(usuario.getIdEmpresa());
				login.setNome(usuario.getNome());
				login.setCargo(usuario.getCargo());
				login.setIdCorretor(usuario.getId());
			}
		}

		return login;
	}

	public Corretor buscaPorLogin(Login login) {
		return this.corretorDAO.buscarPorLogin(login);
	}

	public String buscaPorEmail(String email) {
		return this.corretorDAO.buscarPorEmail(email);
	}

	public Corretor buscaCorretorPorEmail(String email) {
		return this.corretorDAO.buscarCorretorPorEmail(email);
	}

	public boolean excluir(Long id) {
		return this.corretorDAO.excluir(id);
	}

	public List<Corretor> getListCorretorPorIdEmpresa(Parametro parametro) {
		return this.corretorDAO.getListCorretorPorIdEmpresa(parametro);
	}

	public List<Corretor> getListSupervisorPorIdEmpresa(Long id) {
		return this.corretorDAO.getListSupervisorPorIdEmpresa(id);
	}

	public List<Corretor> getListSupervisorPorIdSupervisor(Long id) {
		return this.corretorDAO.getListSupervisorPorIdSupervisor(id);
	}

	public List<Corretor> getListCorretorPorIdSupervisor(Long id) {
		return this.corretorDAO.getListCorretorPorIdSupervisor(id);
	}

	public List<Corretor> getListCorretorPorIdCorretor(Long id) {
		return this.corretorDAO.getListCorretorPorIdCorretor(id);
	}

	public List<Corretor> getListSupervisorPorIdCorretor(Long id) {
		return this.corretorDAO.getListSupervisorPorIdCorretor(id);
	}

	public Corretor buscaPorId(Long id) {
		return this.corretorDAO.carregar(id);
	}

	public void vincularCorretor(Long idCorretor, Long idEmpresa, Long idEmpresaNova) {
		this.corretorDAO.vincularCorretor(idCorretor, idEmpresa, idEmpresaNova);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}