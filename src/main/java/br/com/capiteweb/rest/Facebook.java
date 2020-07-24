package br.com.capiteweb.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.Lead;
import com.facebook.ads.sdk.UserLeadGenFieldData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.capiteweb.commons.Util;

@Path("/facebook")
public class Facebook {
	
	@GET
	@Path("/teste")
	public String teste(@QueryParam("input") String input, @QueryParam("id") String id) {
		 String access_token = "EAAECKYhAJ0wBAJnUfKmRnCu4NCELGUJPi7O1nomLnotg8SpFjIXZAafSnWlwqZAuy3QvZCHwTeG8pMGODYdcbNZAx0sMYm8Frubw2t6GIQlAHkXJDerwKyEKHldgWAqRZCQ3nYhQYkiuA9WAenlMN7Qa8RuR2At1XZBx2L3X42OaMRoj7TcJiQUADTjzS3JZBkZD";
				    //id = "1609930275842070";
		 try {
				Util.EnviarEmail(id, "andersonfabio.1976@gmail.com", "Facebook Hook");
			} catch (ParseException e) {
				e.printStackTrace();
			}
		 			String app_secret = "ab089897f1cdc071027b07bb655a2117";
				    String app_id = "283852379531084";
				    APIContext context = new APIContext(access_token).enableDebug(true);
				    Lead lead = null;
					try {
						lead = new Lead(id, context).get().execute();
					} catch (APIException e1) {
						e1.printStackTrace();
					}
				    lead.getFieldAdsetName();
				    lead.getFieldFormId();
				    List<UserLeadGenFieldData> lista = lead.getFieldFieldData();
				    String nome = null;
				    String fone = null;
				    String form = null;
				    for(UserLeadGenFieldData item : lista) {
				    	if(item.getFieldName().equals("FULL_NAME")) {
				    		nome = item.getFieldValues().get(0);
				    	}
				    	if(item.getFieldName().equals("PHONE")) {
				    		fone = item.getFieldValues().get(0);
				    	}
				    	if(item.getFieldName().equals("form_name")) {
				    		form = item.getFieldValues().get(0);
				    	}
				    	
				    }
		
		
		try {
			Util.EnviarEmail(nome+" "+fone+" "+form, "andersonfabio.1976@gmail.com", "Facebook Hook");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	@Path("/webhookInclusao")
	@Produces("text/plain")
	@GET
	public String webhookInclusao(@QueryParam("hub.challenge") String challenge, @QueryParam("hub.verify_token") String verifyToken,@QueryParam("input") String input) {
		System.out.println("Verify "+verifyToken);
		System.out.println("challenge "+challenge);
		if(verifyToken.equals("4840b6bee2d0aaf7df664bf772ec4fa2")) {
			
			return challenge;
		}
		System.out.println("INPUT: "+input);
		return "";
	}
	
	@POST
	@Consumes({"application/json"})
	@Path("/webhook")
	public void webhook(Leadgen leadgen) throws ParseException {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		String retorno = gson.toJson(leadgen);
		System.out.println(retorno);
		 Util.EnviarEmail(retorno, "andersonfabio.1976@gmail.com", "JSON");
	}
	
	@GET
	@Path("/testepost")
	public String teste2() throws ParseException {
		HttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost("https://graph.facebook.com/v7.0/283852379531084/subscriptions");
		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("object", "page"));
		params.add(new BasicNameValuePair("callback_url", "https://www.capiteweb.com.br/CapiteWeb/rest/facebook/webhook"));
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
		        String content =  EntityUtils.toString(respEntity);
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