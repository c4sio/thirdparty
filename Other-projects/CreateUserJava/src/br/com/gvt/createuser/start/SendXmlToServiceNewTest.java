package br.com.gvt.createuser.start;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import br.com.gvt.createuser.file.FileFunctions;

public class SendXmlToServiceNewTest {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			KeyStoreException, KeyManagementException, ClientProtocolException,
			IOException {
		
		FileFunctions fileFunctions = new FileFunctions();
		fileFunctions.writeInFile("teste_tv");

		SSLContextBuilder sslctxb = new SSLContextBuilder();
		sslctxb.loadTrustMaterial(
				KeyStore.getInstance(KeyStore.getDefaultType()),
				new TrustSelfSignedStrategy() {
					@Override
					public boolean isTrusted(X509Certificate[] chain,
							String authType) throws CertificateException {
						return true;
					}
				});

		SSLContext sslctx = sslctxb.build();
		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setSslcontext(sslctx);

		httpClientBuilder.setHostnameVerifier(new X509HostnameVerifier() {
			@Override
			public void verify(String host, SSLSocket ssl) throws IOException {
			}

			@Override
			public void verify(String host, X509Certificate cert)
					throws SSLException {
			}

			@Override
			public void verify(String host, String[] cns, String[] subjectAlts)
					throws SSLException {
			}

			@Override
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});

		HttpClient httpClient = httpClientBuilder.build();
		HttpPost post = new HttpPost(
				"https://192.168.1.104/GAL/OProv_Management");

		post.addHeader("Content-Type", "text/xml; charset=UTF-8");
		post.addHeader("Accept", "text/xml, multipart/related");
		post.addHeader("User-Agent", "JAX-WS RI 2.2.7-b01");
		
		StringBuilder xmlString = new StringBuilder();
		xmlString.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:loc=\"http://www.telefonica.com/wsdl/UNICA/SOAP/OProv_Management/v7/local\" xmlns:v7=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Management/v7/\" xmlns:v1=\"http://www.telefonica.com/schemas/UNICA/SOAP/common/v1\" xmlns:v6=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Management/v7/\">");
		xmlString.append("   <soapenv:Header xmlns:wsa=\"http://www.w3.org/2005/08/addressing\">");
		xmlString.append("      <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">");
		xmlString.append("         <wsse:UsernameToken wsu:Id=\"UsernameToken-1\">");
		xmlString.append("            <wsse:Username>gvp_app</wsse:Username>");
		xmlString.append("            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">gvp_123456</wsse:Password>");
		xmlString.append("            <wsse:Nonce EncodingType=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-soap-message-security-1.0#Base64Binary\">XCmvf5C7oZZzA861Yf4e6w==</wsse:Nonce>");
		xmlString.append("            <wsu:Created>2012-09-13T13:30:57.067Z</wsu:Created>");
		xmlString.append("         </wsse:UsernameToken>");
		xmlString.append("      </wsse:Security>");
		xmlString.append("      <wsa:Action>urn:createUser</wsa:Action>");
		xmlString.append("      <wsa:From>");
		xmlString.append("         <wsa:Address>http://telefonica.com/Brazil/GVT/Provisioning/OProv_Management</wsa:Address>");
		xmlString.append("      </wsa:From>");
		xmlString.append("      <wsa:To>http://telefonica.com/GVP/OTT/Provisioning/OProv_Management</wsa:To>");
		xmlString.append("   </soapenv:Header>");
		xmlString.append("   <soapenv:Body>");
		xmlString.append("      <loc:createUser>");
		xmlString.append("         <loc:userCreation>");
		xmlString.append("            <v7:userNickName>");
		xmlString.append("               <v1:alias>" + "teste_tv" + "</v1:alias>");
		xmlString.append("            </v7:userNickName>");
		xmlString.append("            <v7:userPassword>"+ "teste" + "</v7:userPassword>");
		xmlString.append("            <v7:email>" + "teste@teste.com" + "</v7:email>");
		xmlString.append("            <v7:verificationMessage>");
		xmlString.append("               <v7:body/>");
		xmlString.append("            </v7:verificationMessage>");
		xmlString.append("            <v7:channel>0</v7:channel>");
		xmlString.append("         </loc:userCreation>");
		xmlString.append("      </loc:createUser>");
		xmlString.append("   </soapenv:Body>");
		xmlString.append("</soapenv:Envelope>");
		
		StringEntity userEntity = new StringEntity(String.valueOf(xmlString));
		post.setEntity(userEntity);
		
		HttpResponse httpResponse = httpClient.execute(post);
		HttpEntity responseEntity = httpResponse.getEntity();

		String responseString = EntityUtils.toString(responseEntity, "UTF-8");
		System.out.println(String.valueOf(responseString));
	}

}
