package br.com.capiteweb.business;

import br.com.capiteweb.DAO.ClienteDAO;
import br.com.capiteweb.model.Cliente;
import br.com.capiteweb.model.Parametro;
import java.util.List;
import javax.persistence.EntityManager;

public class ClienteBusiness {
	EntityManager em;
	private ClienteDAO clienteDAO;

	public ClienteBusiness(EntityManager em) {
		this.em = em;
		this.clienteDAO = new ClienteDAO(em);
	}

	public EntityManager getEm() {
		return this.em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public Cliente carregar(Long idCliente) {
		return this.clienteDAO.carregar(idCliente);
	}

	public Cliente salvar(Cliente cliente) {
		return this.clienteDAO.salvar(cliente);
	}

	public Cliente buscaPorId(Long id) {
		return this.clienteDAO.buscarPorId(id);
	}

	public List<Cliente> buscaPorNome(Parametro parametro) {
		return this.clienteDAO.buscarPorNome(parametro);
	}
	
	public List<Cliente> buscaPorNomeSituacao(Parametro parametro) {
		return this.clienteDAO.buscarPorNomeSituacao(parametro);
	}

	public List<Cliente> getListPorEmpresa(Long id) {
		return this.clienteDAO.getListPorEmpresa(id);
	}

	public boolean excluir(long id) {
		return this.clienteDAO.excluir(id);
	}
}