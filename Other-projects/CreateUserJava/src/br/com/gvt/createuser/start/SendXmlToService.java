package br.com.gvt.createuser.start;


import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;

/**
 * @author GVT
 * 
 */
public class SendXmlToService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SendXmlToService sendXmlToService = new SendXmlToService();
		sendXmlToService.execute();
	}

	/**
	 * Execute
	 */
	private void execute() {
		try {
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost post = new HttpPost(
					"http://GALexterior.localdomain:8011/UNICA_SDP/OProv_Management/OTTUserManager");
			post.addHeader("Content-Type", "text/xml; charset=UTF-8");
			post.addHeader("Accept","text/xml, multipart/related");
			post.addHeader("User-Agent", "JAX-WS RI 2.2.7-b01");
			
			StringBuilder xmlString = new StringBuilder();
			xmlString.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			xmlString.append("<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">");
				xmlString.append("<S:Header>");
					xmlString.append("<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\" S:mustUnderstand=\"1\">");
						xmlString.append("<wsse:UsernameToken wsu:Id=\"UsernameToken-C5ECB7C4A8F3A86F5814345538241931\">");
							xmlString.append("<wsse:Username>gvp_app</wsse:Username>");
							xmlString.append("<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">gvp_123456</wsse:Password>");
							xmlString.append("<wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">83WUqsafyfz5YtKsuctMGQ==</wsse:Nonce>");
							xmlString.append("<wsu:Created>2015-06-17T15:10:24.190Z</wsu:Created>");
						xmlString.append("</wsse:UsernameToken>");
					xmlString.append("</wsse:Security>");
					xmlString.append("<To xmlns=\"http://www.w3.org/2005/08/addressing\">http://GALexterior.localdomain:8011/UNICA_SDP/OProv_Management/OTTUserManager</To>");
					xmlString.append("<Action xmlns=\"http://www.w3.org/2005/08/addressing\">urn:getUserDetails</Action>");
					xmlString.append("<ReplyTo xmlns=\"http://www.w3.org/2005/08/addressing\">");
						xmlString.append("<Address>http://www.w3.org/2005/08/addressing/anonymous</Address>");
					xmlString.append("</ReplyTo>");
					xmlString.append("<FaultTo xmlns=\"http://www.w3.org/2005/08/addressing\">");
						xmlString.append("<Address>http://www.w3.org/2005/08/addressing/anonymous</Address>");
					xmlString.append("</FaultTo>");
					xmlString.append("<MessageID xmlns=\"http://www.w3.org/2005/08/addressing\">uuid:4b19cf9f-8858-4eb6-bd9c-8ca387fed3ef</MessageID>");
					xmlString.append("<From xmlns=\"http://www.w3.org/2005/08/addressing\">");
						xmlString.append("<Address>http://telefonica.com/Vivo/Provisioning/LOCALSIMULATOR/OProv_Management</Address>");
					xmlString.append("</From>");
				xmlString.append("</S:Header>");
				xmlString.append("<S:Body>");
					xmlString.append("<ns3:getUserDetails xmlns=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Management/v7/\" xmlns:ns2=\"http://www.telefonica.com/schemas/UNICA/SOAP/common/v1\" xmlns:ns3=\"http://www.telefonica.com/wsdl/UNICA/SOAP/OProv_Management/v7/local\" xmlns:ns4=\"http://www.telefonica.com/wsdl/UNICA/SOAP/common/v1/faults\">");
						xmlString.append("<ns3:userIdentity>");
							xmlString.append("<ns2:alias>123213213312</ns2:alias>");
						xmlString.append("</ns3:userIdentity>");
					xmlString.append("</ns3:getUserDetails>");
				xmlString.append("</S:Body>");
			xmlString.append("</S:Envelope>");
			
			StringEntity userEntity = new StringEntity(String.valueOf(xmlString));
			post.setEntity(userEntity);
			
			// Executa a acao
			HttpResponse response = httpClient.execute(post);
			HttpEntity responseEntity = response.getEntity();
			
			if (responseEntity != null) {
				String responseString = EntityUtils.toString(responseEntity,
						"UTF-8");
				System.out.println(String.valueOf(responseString.replaceAll("\n", "")));
				InputStream in = IOUtils.toInputStream(responseString, "UTF-8");
				
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();
				Document docXml = dBuilder.parse(in);
				docXml.getDocumentElement().normalize();
				
//				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//				DocumentBuilder builder = factory.newDocumentBuilder();
//				Document document = builder.parse(new InputSource(new StringReader(sb.toString())));
//	 
//				XPathFactory xPathfactory = XPathFactory.newInstance();
//				XPath xpath = xPathfactory.newXPath();
//				XPathExpression expr = xpath
//						.compile("/company/company_name/company_item/size/aiow_detail/no_of_downloads[@name='facebook-members']");
//				String numberOfDownloads = expr.evaluate(document, XPathConstants.STRING).toString();
//				System.out.println(numberOfDownloads);
			
				EntityUtils.consume(responseEntity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
