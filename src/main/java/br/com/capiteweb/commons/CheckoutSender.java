package br.com.capiteweb.commons;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class CheckoutSender {
	private static HttpURLConnection con;

	public String sender(String url, String urlParameters, Checkout checkout) throws IOException {
		DecimalFormat formatter = new DecimalFormat("#0.00");
		Double result = Double.valueOf(((Item) checkout.getItems().get(0)).getAmount().toString());
		((Item) checkout.getItems().get(0)).setAmount(formatter.format(result).replace(",", "."));
		String postData = this.jaxbObjectToXML(checkout);
		String content = null;
		try {
			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/xml; charset=ISO-8859-1");

			DataOutputStream wr;
			try {
				wr = new DataOutputStream(con.getOutputStream());

				try {
					wr.writeChars(postData);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (wr != null) {
						wr.close();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				try {
					

					String line;
					while ((line = br.readLine()) != null) {
						content = line;
					}
				} finally {
					if (br != null) {
						br.close();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} finally {
			con.disconnect();
		}

		return content;
	}

	public Transaction requestTransaction(String url) throws ProtocolException {
		StringBuilder content = null;

		try {
			URL myurl = new URL(url);
			con = (HttpURLConnection) myurl.openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.setRequestProperty("User-Agent", "Java client");
			con.setRequestProperty("Content-Type", "application/xml; charset=ISO-8859-1");

			try {
				DataOutputStream wr = new DataOutputStream(con.getOutputStream());
				if (wr != null) {
					wr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

				try {
					content = new StringBuilder();

					String line;
					while ((line = br.readLine()) != null) {
						content.append(line);
						//content.append(System.lineSeparator());
					}
				} finally {
					if (br != null) {
						br.close();
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				con.disconnect();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {

		}

		return this.xmlToTransaction(content.toString());
	}

	public String jaxbObjectToXML(Checkout checkout) {
		String xmlString = "";

		try {
			JAXBContext context = JAXBContext.newInstance(Checkout.class);
			Marshaller m = context.createMarshaller();
			m.setProperty("jaxb.formatted.output", Boolean.TRUE);
			StringWriter sw = new StringWriter();
			m.marshal(checkout, sw);
			xmlString = sw.toString();
		} catch (JAXBException var6) {
			var6.printStackTrace();
		}

		return xmlString;
	}

	public CheckoutRetorno xmlToObject(String xmlString) {
		CheckoutRetorno retorno = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(CheckoutRetorno.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			retorno = (CheckoutRetorno) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return retorno;
	}

	public Transaction xmlToTransaction(String xmlString) {
		Transaction retorno = null;

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Transaction.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			retorno = (Transaction) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
		} catch (Exception var5) {
			var5.printStackTrace();
		}

		return retorno;
	}
}