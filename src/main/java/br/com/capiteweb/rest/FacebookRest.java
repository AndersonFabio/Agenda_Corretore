package br.com.capiteweb.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.mail.internet.ParseException;
import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.Lead;
import com.facebook.ads.sdk.UserLeadGenFieldData;

import br.com.capiteweb.business.AgendaBusiness;
import br.com.capiteweb.business.ClienteBusiness;
import br.com.capiteweb.business.CorretorBusiness;
import br.com.capiteweb.business.EmpresaBusiness;
import br.com.capiteweb.business.FormLeadgenBusiness;
import br.com.capiteweb.business.MidiaBusiness;
import br.com.capiteweb.business.PageLeadgenBusiness;
import br.com.capiteweb.business.SituacaoBusiness;
import br.com.capiteweb.commons.HibernateUtil;
import br.com.capiteweb.commons.Util;
import br.com.capiteweb.model.Agenda;
import br.com.capiteweb.model.Cliente;
import br.com.capiteweb.model.Corretor;
import br.com.capiteweb.model.Empresa;
import br.com.capiteweb.model.FormLeadgen;
import br.com.capiteweb.model.Login;
import br.com.capiteweb.model.PageLeadgen;
import br.com.capiteweb.model.Parametro;

@Path("/facebook")
public class FacebookRest {

	private EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
	private EmpresaBusiness empresaBusiness;
	private SituacaoBusiness situacaoBusiness;
	private MidiaBusiness midiaBusiness;
	private FormLeadgenBusiness formLeadgenBusiness;
	private PageLeadgenBusiness pageLeadgenBusiness;
	private ClienteBusiness clienteBusiness;
	private AgendaBusiness agendaBusiness;
	private CorretorBusiness corretorBusiness;

	public FacebookRest() {
		this.empresaBusiness = new EmpresaBusiness(this.em);
		this.situacaoBusiness = new SituacaoBusiness(this.em);
		this.midiaBusiness = new MidiaBusiness(this.em);
		this.formLeadgenBusiness = new FormLeadgenBusiness(this.em);
		this.pageLeadgenBusiness = new PageLeadgenBusiness(this.em);
		this.clienteBusiness = new ClienteBusiness(this.em);
		this.agendaBusiness = new AgendaBusiness(this.em);
		this.corretorBusiness = new CorretorBusiness(this.em);
	}
	
	
	@GET
	@Path("/teste")
	public String teste(@QueryParam("input") String input, @QueryParam("id") String id) {
		String access_token = "EAAECKYhAJ0wBAL1igCbvotMxm4HaPt6N922BNfZCW9zaD8Yp6OZCVl34EpZBRR7v4SQErnVXJs8KZA0aNi9zWTRkHZAVq2XB327Qbzg9buzQl4mB5s6R9ZCZCo6LqIblwiib85AEutdwS5voZASFLIehZBmsQES9WhUKQ9Wn67ta3gZCTHZBIpZAN9LP";
		
		// id = "1609930275842070";
		/*try {
			Util.EnviarEmail(id, "andersonfabio.1976@gmail.com", "Facebook Hook");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String senha = "ab089897f1cdc071027b07bb655a2117";

       
        
		String appSecret = Util.encode(senha, access_token);
		CloseableHttpClient httpclient = HttpClients.createDefault();
	      HttpGet httpget = new HttpGet("https://graph.facebook.com/v8.0/"+id+"/?access_token="+access_token+"&appsecret_proof="+appSecret);
	      HttpResponse httpresponse = null;
	      Scanner sc = null;
		try {
			httpresponse = httpclient.execute(httpget);
			sc = new Scanner(httpresponse.getEntity().getContent());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      

	      //Printing the status line
	      System.out.println(httpresponse.getStatusLine());
	      while(sc.hasNext()) {
	         System.out.println(sc.nextLine());
	      }
		
		*/
		return "";
	}

	@Path("/register")
	@Consumes({ "application/json" })
	@Produces({ "application/json" })
	@POST
	public Response register(ListaPaginas listaPaginas) {
		HttpClient httpClient = new DefaultHttpClient();

		HttpPost httpPost = new HttpPost();

		DefaultHttpClient httpclient = new DefaultHttpClient();
		Response mResponse = new Response();
		String content = "";
		String retorno = "";
		for (GetId items : listaPaginas.getData()) {
			HttpPost post = new HttpPost("https://graph.facebook.com/v7.0/" + items.getId() + "/subscribed_apps");
			post.setHeader("Content-Type", "application/json");
			post.setHeader("access_token", items.getAccess_token());

			JSONObject json = new JSONObject();
			json.put("access_token", items.getAccess_token());
			json.put("subscribed_fields", "leadgen");

			StringEntity params = null;
			try {
				params = new StringEntity(json.toString());
				post.setEntity(params);
				HttpResponse response = httpclient.execute(post);
				HttpEntity respEntity = response.getEntity();
				
				if (respEntity != null) {
					content = EntityUtils.toString(respEntity);
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			items.getIdEmpresa();
			
			PageLeadgen pageLeadgen = pageLeadgenBusiness.buscaPorIdPage(items.getId());
			if(pageLeadgen.getIdEmpresa() == null) {
				pageLeadgen.setIdEmpresa(Long.valueOf(items.getIdEmpresa()));
				pageLeadgen.setIdPage(items.getId());
				pageLeadgenBusiness.salvar(pageLeadgen);
			}
			
			
			retorno = retorno + " "+content;
			
		}
		httpclient.getConnectionManager().shutdown();
		mResponse.setResponse(retorno);
		return mResponse;
	}

	@Path("/webhookInclusao")
	@Produces("text/plain")
	@GET
	public String webhookInclusao(@QueryParam("hub.challenge") String challenge,
			@QueryParam("hub.verify_token") String verifyToken, @QueryParam("input") String input) {
		System.out.println("Verify " + verifyToken);
		System.out.println("challenge " + challenge);
		if (verifyToken.equals("4840b6bee2d0aaf7df664bf772ec4fa2")) {

			return challenge;
		}
		System.out.println("INPUT: " + input);
		return "";
	}

	@POST
	@Consumes({ "application/json" })
	@Path("/webhook")
	public void webhook(Leadgen leadgen) throws ParseException, java.text.ParseException {
		String formId = leadgen.getEntry().get(0).getChanges().get(0).getValue().getForm_id();
		String leadId = leadgen.getEntry().get(0).getChanges().get(0).getValue().getLeadgen_id();
		String pageId = leadgen.getEntry().get(0).getId();
		
		PageLeadgen pageLeadgen = pageLeadgenBusiness.buscaPorIdPage(pageId);
		
		FormLeadgen formLeadgen = formLeadgenBusiness.buscaPorIdForm(formId);
		Parametro parametro = new Parametro();
		Login login = new Login();
		if(formLeadgen.getIdEmpresa() == null) {
			login.setIdEmpresa(pageLeadgen.getIdEmpresa());
		} else {
			login.setIdEmpresa(formLeadgen.getIdEmpresa());
		}
		parametro.setLogin(login);
		parametro.setPesquisar("Facebook");
		if(formLeadgen.getIdEmpresa() == null) {
			formLeadgen.setIdEmpresa(pageLeadgen.getIdEmpresa());
			formLeadgen.setIdForm(formId);
			formLeadgen.setIdMidia(midiaBusiness.buscaPorNome(parametro).get(0).getId());
			parametro.setPesquisar("Contato");
			formLeadgen.setIdSituacao(situacaoBusiness.buscaPorNome(parametro).get(0).getId());
			formLeadgenBusiness.salvar(formLeadgen);
		}
		
		
		String access_token = "EAAECKYhAJ0wBAL1igCbvotMxm4HaPt6N922BNfZCW9zaD8Yp6OZCVl34EpZBRR7v4SQErnVXJs8KZA0aNi9zWTRkHZAVq2XB327Qbzg9buzQl4mB5s6R9ZCZCo6LqIblwiib85AEutdwS5voZASFLIehZBmsQES9WhUKQ9Wn67ta3gZCTHZBIpZAN9LP";
		String app_secret = "ab089897f1cdc071027b07bb655a2117";
		String app_id = "283852379531084";
		APIContext context = new APIContext(access_token).enableDebug(true);
		Lead lead = null;
		try {
			lead = new Lead(leadId, context).get().execute();
		} catch (APIException e1) {
			Util.EnviarEmail(e1.toString(), "andersonfabio.1976@gmail.com", "ERRO Leadgen!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			e1.printStackTrace();
		}
		lead.getFieldAdsetName();
		lead.getFieldFormId();
		List<UserLeadGenFieldData> lista = lead.getFieldFieldData();
		String nome = null;
		String fone = null;
		String email = null;
		for (UserLeadGenFieldData item : lista) {
			if (item.getFieldName().equals("FULL_NAME")) {
				nome = item.getFieldValues().get(0);
			}
			if (item.getFieldName().equals("PHONE")) {
				fone = item.getFieldValues().get(0);
			}
			if (item.getFieldName().equals("EMAIL")) {
				email = item.getFieldValues().get(0);
			}
		}

		Cliente cliente = new Cliente();
		cliente.setIdEmpresa(formLeadgen.getIdEmpresa());
		cliente.setNome(nome);
		cliente.setFone1(fone.replace("+55", "").replace("p:", ""));
		cliente.setWhatsapp1(true);
		cliente.setIdMidia(formLeadgen.getIdMidia());
		cliente.setIdSituacao(formLeadgen.getIdSituacao());
		cliente.setIdCorretor(formLeadgen.getIdCorretor());
		cliente.setEmail(email);
		clienteBusiness.salvar(cliente);
		
		Agenda agenda = new Agenda();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.HOUR_OF_DAY, -3);
		Date data = c.getTime();

			
		agenda.setData(data);
		agenda.setIdCliente(cliente.getId());
		agenda.setIdEmpresa(formLeadgen.getIdEmpresa());
		agenda.setHistorico("Primeiro Contato");
		agenda.setIdEmpreendimento(formLeadgen.getIdEmpreendimento());
		agendaBusiness.salvar(agenda);
		
		//Empresa empresa = empresaBusiness.buscaPorId(formLeadgen.getIdEmpresa());
		Corretor corretor = corretorBusiness.buscaPorId(formLeadgen.getIdCorretor());
		if(formLeadgen.getIdEmpresa() != pageLeadgen.getIdEmpresa()) {
			Util.EnviarEmail("Você recebeu um leadgen, acesse o CRM para consulta-lo.", corretor.getEmail(), "CapiteWeb CRM - Leadgen");
		}
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
//		String retorno = gson.toJson(leadgen);
//		System.out.println(retorno);
//		Util.EnviarEmail(retorno, "andersonfabio.1976@gmail.com", "JSON");
	}

	@GET
	@Path("/testepost")
	public String teste2() throws ParseException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("https://graph.facebook.com/v7.0/283852379531084/subscriptions");
		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("object", "page"));
		params.add(
				new BasicNameValuePair("callback_url", "https://www.capiteweb.com.br/CapiteWeb/rest/facebook/webhook"));
		params.add(new BasicNameValuePair("fields", "leadgen"));
		params.add(new BasicNameValuePair("verify_token", "4840b6bee2d0aaf7df664bf772ec4fa2"));
		params.add(new BasicNameValuePair("access_token", "283852379531084|5Wsba5XWaToFvb9ZXoq9QpXloNI"));

		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}
		/*
		 * Execute the HTTP Request
		 */
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity respEntity = response.getEntity();

			if (respEntity != null) {
				// EntityUtils to get the response content
				String content = EntityUtils.toString(respEntity);
				Util.EnviarEmail("", "andersonfabio.1976@gmail.com", content);
			}
		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();
		} catch (IOException e) {
			// writing exception to log
			e.printStackTrace();
		}
		return "";
	}

	public void closeSessions() {
		this.empresaBusiness.getEm().close();
		this.situacaoBusiness.getEm().close();
		this.midiaBusiness.getEm().close();
		this.formLeadgenBusiness.getEm().close();
		this.pageLeadgenBusiness.getEm().close();
		this.clienteBusiness.getEm().close();
		this.agendaBusiness.getEm().close();
		this.corretorBusiness.getEm().close();
	}
}

class Response {
	private String response;

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

}

class ListaPaginas {
	private List<GetId> data = new ArrayList<GetId>();

	public List<GetId> getData() {
		return data;
	}

	public void setData(List<GetId> data) {
		this.data = data;
	}
	
}

class GetId {
	private String id;
	private String access_token;
	private String idEmpresa;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

}

class Leadgen {
	private String object;
	private List<Entry> entry;

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public List<Entry> getEntry() {
		return entry;
	}

	public void setEntry(List<Entry> entry) {
		this.entry = entry;
	}

}

class Entry {
	private String id;
	private String time;
	private List<Changes> changes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public List<Changes> getChanges() {
		return changes;
	}

	public void setChanges(List<Changes> changes) {
		this.changes = changes;
	}

}

class Changes {
	private String field;
	private Value value;

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

}

class Value {
	private String ad_id;
	private String form_id;
	private String leadgen_id;
	private String created_time;
	private String page_id;
	private String adgroup_id;

	public String getAd_id() {
		return ad_id;
	}

	public void setAd_id(String ad_id) {
		this.ad_id = ad_id;
	}

	public String getForm_id() {
		return form_id;
	}

	public void setForm_id(String form_id) {
		this.form_id = form_id;
	}

	public String getLeadgen_id() {
		return leadgen_id;
	}

	public void setLeadgen_id(String leadgen_id) {
		this.leadgen_id = leadgen_id;
	}

	public String getCreated_time() {
		return created_time;
	}

	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}

	public String getPage_id() {
		return page_id;
	}

	public void setPage_id(String page_id) {
		this.page_id = page_id;
	}

	public String getAdgroup_id() {
		return adgroup_id;
	}

	public void setAdgroup_id(String adgroup_id) {
		this.adgroup_id = adgroup_id;
	}

	
}