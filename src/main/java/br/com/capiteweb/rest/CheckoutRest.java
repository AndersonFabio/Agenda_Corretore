package br.com.capiteweb.rest;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

import br.com.capiteweb.business.CaptacaoBusiness;
import br.com.capiteweb.business.CheckoutPagamentoBusiness;
import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.LoginBusiness;
import br.com.capiteweb.business.Ret;
import br.com.capiteweb.commons.Checkout;
import br.com.capiteweb.commons.CheckoutRetorno;
import br.com.capiteweb.commons.CheckoutSender;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.commons.Item;
import br.com.capiteweb.commons.Receiver;
import br.com.capiteweb.commons.Shipping;
import br.com.capiteweb.model.Captacao;
import br.com.capiteweb.model.CheckoutPagamento;
import br.com.capiteweb.model.Corretor;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.Parametro;

@Path("/checkout")
public class CheckoutRest {
	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private LoginBusiness loginBusiness;
	private EmpresaBusiness empresaBusiness;
	private CorretorBusiness corretorBusiness;
	private CaptacaoBusiness captacaoBusiness;
	private CheckoutPagamentoBusiness checkoutPagamentoBusiness;
	private String URL = "https://ws.pagseguro.uol.com.br/";
	private String URL2 = "https://pagseguro.uol.com.br/";
	private String TOKEN = "91321c43-7042-470c-9ab5-e243c79d9f3309c4555e4fa3ad5b93b6223d07b962946f82-7226-42cd-b8ab-65b8abba7f37";
	private String EMAIL = "andersonfabio.1976@gmail.com";

	public CheckoutRest() {
		this.loginBusiness = new LoginBusiness(this.em);
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
		this.checkoutPagamentoBusiness = new CheckoutPagamentoBusiness(this.em);
		this.captacaoBusiness = new CaptacaoBusiness(this.em);
	}

	@Path("/pagarAgenda")
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Ret pagarAgenda(Parametro parametro) throws IOException {
		Ret ret = this.pagamento(parametro, "Agenda de Corretores", "50.00", "1");
		return ret;
	}

	@Path("/pagarLed")
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Ret pagarLed(Parametro parametro) throws IOException {
		Ret ret = this.pagamento(parametro, "Captação de Led", "200.00", "2");
		return ret;
	}

	@Path("/pagarCaptacao")
	@POST
	@Consumes({"application/json"})
	@Produces({"application/json"})
	public Ret pagarCaptacao(Parametro parametro) throws IOException {
		Captacao captacao = this.captacaoBusiness.buscaPorId(parametro);
		Ret ret = this.pagamento(parametro, "Campanha de Captação de Led", captacao.getInvestimento1().toString(), "3");
		return ret;
	}

	@Path("/notification")
	@Consumes({"application/x-www-form-urlencoded"})
	@POST
	public void getNotification(MultivaluedMap<String, String> urlParams) throws ProtocolException {
		String referencia = (String) urlParams.getFirst("Referencia");
		String status = (String) urlParams.getFirst("StatusTransacao");
		String transacao = (String) urlParams.getFirst("TransacaoID");
		System.out.println("Referência: " + referencia);
		System.out.println("status: " + status);
		if (status.contains("Pag") || status.contains("Aprovad")) {
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(5, 30);
			Date vencimento = c.getTime();
			CheckoutPagamento checkoutPagamento = this.checkoutPagamentoBusiness.buscaPorId(Long.valueOf(referencia));
			checkoutPagamento.setNotificationCode(transacao);
			checkoutPagamento.setSituacao("Pago");
			this.checkoutPagamentoBusiness.salvar(checkoutPagamento);
			Parametro parametro;
			Captacao captacao;
			if (checkoutPagamento.getCargo().equals("Imobiliaria")) {
				Empresa empresa = this.empresaBusiness.buscaPorId(checkoutPagamento.getIdEmpresa());
				if (checkoutPagamento.getIdProduto().equals("1")) {
					empresa.setVencimento(vencimento);
				} else if (checkoutPagamento.getIdProduto().equals("2")) {
					empresa.setVencimentoLed(vencimento);
				} else if (checkoutPagamento.getIdProduto().equals("3")) {
					parametro = new Parametro();
					parametro.getLogin().setCargo(checkoutPagamento.getCargo());
					parametro.getLogin().setIdCorretor(checkoutPagamento.getIdCorretor());
					parametro.getLogin().setIdEmpresa(checkoutPagamento.getIdEmpresa());
					captacao = this.captacaoBusiness.buscaPorId(parametro);
					captacao.setSituacao1("Pago");
					captacao.setAnuncio2(captacao.getAnuncio1());
					captacao.setData2(captacao.getData1());
					captacao.setInvestimento2(captacao.getInvestimento1());
					captacao.setPalavras2(captacao.getPalavras1());
					captacao.setTitulo2(captacao.getTitulo1());
					captacao.setAnuncio1("");
					captacao.setData1((Date) null);
					captacao.setInvestimento1(0.0F);
					captacao.setPalavras1("");
					captacao.setTitulo1("");
					this.captacaoBusiness.salvar(captacao);
				}

				this.empresaBusiness.salvar(empresa);
			} else {
				Corretor corretor = this.corretorBusiness.buscaPorId(checkoutPagamento.getIdCorretor());
				if (checkoutPagamento.getIdProduto().equals("1")) {
					corretor.setVencimento(vencimento);
				} else if (checkoutPagamento.getIdProduto().equals("2")) {
					corretor.setVencimentoLed(vencimento);
				} else if (checkoutPagamento.getIdProduto().equals("3")) {
					parametro = new Parametro();
					parametro.getLogin().setCargo(checkoutPagamento.getCargo());
					parametro.getLogin().setIdCorretor(checkoutPagamento.getIdCorretor());
					parametro.getLogin().setIdEmpresa(checkoutPagamento.getIdEmpresa());
					captacao = this.captacaoBusiness.buscaPorId(parametro);
					captacao.setSituacao1("Pago");
					captacao.setAnuncio2(captacao.getAnuncio1());
					captacao.setData2(captacao.getData1());
					captacao.setInvestimento2(captacao.getInvestimento1());
					captacao.setPalavras2(captacao.getPalavras1());
					captacao.setTitulo2(captacao.getTitulo1());
					captacao.setAnuncio1("");
					captacao.setData1((Date) null);
					captacao.setInvestimento1(0.0F);
					captacao.setPalavras1("");
					captacao.setTitulo1("");
					this.captacaoBusiness.salvar(captacao);
				}

				this.corretorBusiness.salvar(corretor);
			}
		}

	}

	public Ret pagamento(Parametro parametro, String produto, String preco, String idProduto) throws IOException {
		Checkout checkout = new Checkout();
		Corretor corretor = null;
		Empresa empresa = null;
		//String documentType = null;
		String cargo = parametro.getLogin().getCargo();
		//String documento = null;
		String url = this.URL + "v2/checkout?email=" + this.EMAIL + "&token=" + this.TOKEN;
		String urlParameters = "";
		if (cargo.equals("Imobiliaria")) {
			empresa = this.empresaBusiness.buscaPorId(parametro.getLogin().getIdEmpresa());
			//documento = empresa.getCnpj();
			//documentType = "CNPJ";
		} else {
			corretor = this.corretorBusiness.buscaPorId(parametro.getLogin().getIdCorretor());
			//documentType = "CPF";
			//documento = corretor.getCpf();
		}

		//Sender sender = new Sender();
		CheckoutPagamento checkoutPagamento = new CheckoutPagamento();
		checkoutPagamento.setCargo(cargo.equals("Imobiliaria") ? empresa.getCargo() : corretor.getCargo());
		checkoutPagamento.setData(new Date());
		checkoutPagamento.setIdCaptacao(parametro.getIdCaptacao());
		if (cargo.equals("Imobiliaria")) {
			checkoutPagamento.setIdEmpresa(empresa.getId());
		} else {
			checkoutPagamento.setIdCorretor(corretor.getId());
		}

		//Phone phone;
		//label93 : {
			checkoutPagamento.setIdProduto(idProduto);
			checkoutPagamento.setProduto(produto);
			checkoutPagamento.setPreco(preco);
			checkoutPagamento = this.checkoutPagamentoBusiness.salvar(checkoutPagamento);
			//sender.setName(cargo.equals("Imobiliaria") ? empresa.getNome() : corretor.getNome());
			//sender.setEmail(cargo.equals("Imobiliaria") ? empresa.getEmail() : corretor.getEmail());
			//phone = new Phone();
			//if (cargo.equals("Imobiliaria")) {
			//	if (empresa.getCelular() == null || empresa.getCelular().equals("")) {
			//		break label93;
			//	}
			//} else if (corretor.getCelular() == null || corretor.getCelular().equals("")) {
			//	break label93;
			//}

			//phone.setAreaCode(cargo.equals("Imobiliaria")
			//		? empresa.getCelular().substring(0, 2)
			//		: corretor.getCelular().substring(0, 2));
			//phone.setNumber(cargo.equals("Imobiliaria")
			//		? empresa.getCelular().substring(2, empresa.getCelular().length() - 1)
			//		: corretor.getCelular().substring(2, corretor.getCelular().length() - 1));
		//}

		//sender.setPhone(phone);
		//Document document = new Document();
		//document.setType(documentType);
		//document.setValue(documento);
		//List<Document> documents = new ArrayList();
		//documents.add(document);
		//sender.setDocuments(documents);
		//checkout.setSender(sender);
		checkout.setCurrency("BRL");
		Item item = new Item();
		item.setId(idProduto);
		item.setDescription(produto);
		item.setQuantity("1");
		item.setAmount(preco);
		item.setShippingCost("0.00");
		item.setWeight("0");
		List<Item> items = new ArrayList<Item>();
		items.add(item);
		checkout.setItems(items);
		checkout.setRedirectURL("http://www.capiteweb.com.br");
		checkout.setExtraAmount("0.00");
		checkout.setReference(checkoutPagamento.getId().toString());
		Shipping shipping = new Shipping();
		shipping.setAddressRequired("false");
		shipping.setType("3");
		shipping.setCost("0.00");
		//Address address = new Address();
		//address.setCity(cargo.equals("Imobiliaria") ? empresa.getCidade() : corretor.getCidade());
		//address.setComplement(cargo.equals("Imobiliaria") ? empresa.getComplemento() : corretor.getComplemento());
		//address.setCountry("Brasil");
		//address.setDistrict(cargo.equals("Imobiliaria") ? empresa.getEstado() : corretor.getEstado());
		//address.setNumber(cargo.equals("Imobiliaria") ? empresa.getNumero() : corretor.getNumero());
		//address.setPostalCode(cargo.equals("Imobiliaria") ? empresa.getCep() : corretor.getCep());
		//address.setState(cargo.equals("Imobiliaria") ? empresa.getEstado() : corretor.getEstado());
		//address.setStreet(cargo.equals("Imobiliaria") ? empresa.getEndereco() : corretor.getEndereco());
		//shipping.setAddress(address);
		//checkout.setShipping(shipping);
		checkout.setTimeout("25");
		checkout.setMaxAge("30");
		checkout.setMaxUses("1");
		Receiver receiver = new Receiver();
		receiver.setEmail("andersonfabio.1976@gmail.com");
		checkout.setReceiver(receiver);
		checkout.setEnableRecover("false");
		CheckoutSender checkoutSender = new CheckoutSender();
		String retorno = checkoutSender.sender(url, urlParameters, checkout);
		CheckoutRetorno checkoutRetorno = checkoutSender.xmlToObject(retorno);
		String urlRetorno = this.URL2 + "/v2/checkout/payment.html?code=" + checkoutRetorno.getCode();
		Ret ret = new Ret();
		ret.setUrl(urlRetorno);
		this.closeSessions();
		return ret;
	}

	public void closeSessions() {
		this.loginBusiness.getEm().close();
		this.empresaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
		this.checkoutPagamentoBusiness.getEm().close();
	}
}