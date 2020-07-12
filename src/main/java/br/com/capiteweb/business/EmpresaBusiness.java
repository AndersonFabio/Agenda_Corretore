package br.com.capiteweb.business;

import br.com.capiteweb.DAO.EmpresaDAO;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Parametro;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public class EmpresaBusiness {
	private EntityManager em;
	private EmpresaDAO empresaDAO;

	public EmpresaBusiness(EntityManager em) {
		this.em = em;
		this.empresaDAO = new EmpresaDAO(em);
	}

	public Empresa carregar(Long idEmpresa) {
		return this.empresaDAO.carregar(idEmpresa);
	}

	public Login checkLogin(Login login) {
		Empresa usuario = this.buscaPorLogin(login);
		login.setAcesso("N");
		if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
			if ((new Date()).after(usuario.getVencimento())) {
				login.setAcesso("N");
			} else {
				login.setAcesso("S");
				login.setIdEmpresa(usuario.getId());
				login.setNome(usuario.getNome());
			}
		}

		return login;
	}

	public Login checkLogin(Parametro parametro) {
		new Login();
		Login login = parametro.getLogin();
		Empresa usuario = this.buscaPorLogin(login);
		login.setAcesso("N");
		if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
			if ((new Date()).after(usuario.getVencimento())) {
				login.setAcesso("N");
			} else {
				login.setAcesso("S");
				login.setIdEmpresa(usuario.getId());
				login.setNome(usuario.getNome());
			}
		}

		return login;
	}

	public Empresa buscaPorLogin(Login login) {
		return this.empresaDAO.buscarPorLogin(login);
	}

	public Empresa salvar(Empresa empresa) {
		return this.empresaDAO.salvar(empresa);
	}

	public String buscaPorEmail(String email) {
		return this.empresaDAO.buscarPorEmail(email);
	}

	public Empresa buscaPorId(Long id) {
		return this.empresaDAO.buscarPorId(id);
	}

	public Empresa buscaPorNome(String nome) {
		return this.empresaDAO.buscarPorNome(nome);
	}

	public List<Empresa> getListAll() {
		return this.empresaDAO.getListAll();
	}

	public boolean excluir(long id) {
		return this.empresaDAO.excluir(id);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}