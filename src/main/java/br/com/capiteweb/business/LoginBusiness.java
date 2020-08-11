package br.com.capiteweb.business;

import br.com.capiteweb.DAO.CorretorDAO;
import br.com.capiteweb.DAO.EmpresaDAO;
import br.com.capiteweb.model.Corretor;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.Parametro;
import java.util.Date;
import javax.persistence.EntityManager;

public class LoginBusiness {
	EntityManager em;
	private EmpresaDAO empresaDAO;
	private CorretorDAO corretorDAO;

	public LoginBusiness(EntityManager em) {
		this.em = em;
		this.empresaDAO = new EmpresaDAO(em);
		this.corretorDAO = new CorretorDAO(em);
	}

	public Login checkLogin(Login login) {
		if (login.getCargo().equals("Imobiliaria")) {
			Empresa usuario = this.empresaDAO.buscarPorLogin(login);
			login.setAcesso("N");
			if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
				if ((new Date()).after(usuario.getVencimento())) {
					login.setAcesso("S");
					login.setVencido(true);
					login.setIdEmpresa(usuario.getId());
					login.setNome(usuario.getNome());
				} else {
					login.setVencido(false);
					login.setAcesso("S");
					login.setIdEmpresa(usuario.getId());
					login.setNome(usuario.getNome());
				}

				login.setNome(usuario.getNome());
			}
		} else {
			Corretor usuario = this.corretorDAO.buscarPorLogin(login);
			if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
				login.setAcesso("N");
				if (!(new Date()).after(usuario.getVencimento()) && login.getAcesso() != null) {
					login.setAcesso("S");
					login.setIdEmpresa(usuario.getIdEmpresa());
					login.setNome(usuario.getNome());
					login.setCargo(usuario.getCargo());
					login.setIdCorretor(usuario.getId());
				} else {
					login.setAcesso("S");
					login.setIdEmpresa(usuario.getIdEmpresa());
					login.setNome(usuario.getNome());
					login.setCargo(usuario.getCargo());
					login.setIdCorretor(usuario.getId());
					login.setVencido(true);
				}

				login.setNome(usuario.getNome());
			}
		}

		return login;
	}

	public Login checkLogin(Parametro parametro) {
		new Login();
		Login login = parametro.getLogin();
		if (login.getCargo().equals("Imobiliaria")) {
			Empresa usuario = this.empresaDAO.buscarPorLogin(login);
			login.setAcesso("N");
			if (usuario != null && usuario.getEmail() != null && usuario.getEmail().equals(login.getEmail())) {
				if ((new Date()).after(usuario.getVencimento())) {
					login.setAcesso("N");
				} else {
					login.setAcesso("S");
					login.setIdEmpresa(usuario.getId());
					login.setNome(usuario.getNome());
				}

				login.setNome(usuario.getNome());
			}
		} else {
			Corretor usuario = this.corretorDAO.buscarPorLogin(login);
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

				login.setNome(usuario.getNome());
			}
		}

		return login;
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}