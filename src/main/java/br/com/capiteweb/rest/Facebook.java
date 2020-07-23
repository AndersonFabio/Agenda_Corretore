package br.com.capiteweb.rest;

import java.util.List;

import javax.mail.internet.ParseException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import com.facebook.ads.sdk.Lead;
import com.facebook.ads.sdk.UserLeadGenFieldData;
import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;

import br.com.capiteweb.commons.Util;

@Path("/facebook")
public class Facebook {
	
	@GET
	@Path("/teste")
	public void teste(@QueryParam("input") String input, @QueryParam("id") String id) {
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
	}

}
