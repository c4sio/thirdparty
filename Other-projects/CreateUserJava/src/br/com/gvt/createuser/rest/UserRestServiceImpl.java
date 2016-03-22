package br.com.gvt.createuser.rest;

import java.io.IOException;
import java.io.StringReader;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import br.com.gvt.createuser.exception.ClientException;
import br.com.gvt.createuser.exception.ServerException;
import br.com.gvt.createuser.vo.UserVO;

public class UserRestServiceImpl implements UserRestService {

	static Logger logger = Logger
			.getLogger(UserRestServiceImpl.class.getName());

	private static String retornoXml = "";

	private static final String URL_USER = "https://213.140.61.61/GAL/OProv_Management";
	private static final String URL_SERVICE = "https://213.140.61.61/GAL/OProv_Subscriptions";

	@Override
	public UserVO createUser(UserVO userVo) throws ClientException,
			ServerException {

		PropertyConfigurator.configure("log4j.properties");
		System.out.println("Arquivo de log inicializado.");
		System.out.println("Entrou no createUser");

		String responseService = null;
		retornoXml = "";

		try {
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
				public void verify(String host, SSLSocket ssl)
						throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert)
						throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns,
						String[] subjectAlts) throws SSLException {
				}

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			HttpClient httpClient = httpClientBuilder.build();
			HttpPost post = new HttpPost(URL_USER);
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
			xmlString.append("               <v1:alias>" + userVo.getSubscreberId() + "</v1:alias>");
			xmlString.append("            </v7:userNickName>");
			xmlString.append("            <v7:userPassword>" + userVo.getPassword() + "</v7:userPassword>");
			xmlString.append("            <v7:email>" + userVo.getEmail() + "</v7:email>");
			xmlString.append("            <v7:verificationMessage>");
			xmlString.append("               <v7:body/>");
			xmlString.append("            </v7:verificationMessage>");
			xmlString.append("            <v7:channel>0</v7:channel>");
			xmlString.append("         </loc:userCreation>");
			xmlString.append("      </loc:createUser>");
			xmlString.append("   </soapenv:Body>");
			xmlString.append("</soapenv:Envelope>");

			StringEntity userEntity = new StringEntity(
					String.valueOf(xmlString));
			post.setEntity(userEntity);

			// Executa a acao
			HttpResponse httpResponse = httpClient.execute(post);
			HttpEntity responseEntity = httpResponse.getEntity();

			String responseString = EntityUtils.toString(responseEntity,
					"UTF-8");
			System.out.println(String.valueOf(responseString));

			// Converto a String em XML
			Document document = convertStringToDocument(responseString);

			// Varre os campos do XML de retorno
			if (document.hasChildNodes()) {
				document.getDocumentElement().normalize();
				responseService = printNote(document.getChildNodes(), "");
			}

			EntityUtils.consume(responseEntity);

			if ("".equalsIgnoreCase(responseService)) {
				// responseService = "Criado globalUserID = "
				// + userVo.getSubscreberId();
				logger.info("\n ===================================\n");
				logger.info("Criado globalUserID: " + userVo.getSubscreberId());
				logger.info("Email: " + userVo.getEmail());
				logger.info("Password: " + userVo.getPassword());
				logger.info("AndroidId: " + userVo.getAndroidId());
				logger.info("AppleId: " + userVo.getAppleId());
				logger.info("\n ===================================\n");
				userVo.setError(false);
			} else {
				responseService = "Erro ao criar usuário: " + responseService;
				logger.info("Erro ao criar usuário: " + responseService);
				userVo.setError(true);
			}

			userVo.setRetorno(responseService);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return userVo;
	}

	@Override
	public UserVO activeUser(UserVO userVo) throws ClientException,
			ServerException {
		try {
			String responseService = null;
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
				public void verify(String host, SSLSocket ssl)
						throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert)
						throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns,
						String[] subjectAlts) throws SSLException {
				}

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			HttpClient httpClient = httpClientBuilder.build();
			HttpPost post = new HttpPost(URL_SERVICE);
			post.addHeader("Content-Type", "text/xml; charset=UTF-8");
			post.addHeader("Accept", "text/xml, multipart/related");
			post.addHeader("User-Agent", "JAX-WS RI 2.2.7-b01");

			StringBuilder xmlString = new StringBuilder();
			xmlString.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
			xmlString.append("   <soap:Header>");
			xmlString.append("      <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">");
			xmlString.append("         <wsse:UsernameToken wsu:Id=\"UsernameToken-2\">");
			xmlString.append("            <wsse:Username>gvp_app</wsse:Username>");
			xmlString.append("            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">gvp_123456</wsse:Password>");
			xmlString.append("         </wsse:UsernameToken>");
			xmlString.append("      </wsse:Security>");
			xmlString.append("      <Action xmlns=\"http://www.w3.org/2005/08/addressing\">urn:activateService</Action>");
			xmlString.append("      <To xmlns=\"http://www.w3.org/2005/08/addressing\">http://telefonica.com/GVP/OTT/Provisioning/OProv_Subscriptions</To>");
			xmlString.append("      <From xmlns=\"http://www.w3.org/2005/08/addressing\">");
			xmlString.append("         <Address>http://telefonica.com/Brazil/GVT/Provisioning/OProv_Subscriptions</Address>");
			xmlString.append("      </From>");
			xmlString.append("   </soap:Header>");
			xmlString.append("   <soap:Body>");
			xmlString.append("      <ns2:activateService xmlns=\"http://www.telefonica.com/schemas/UNICA/SOAP/common/v1\" xmlns:ns2=\"http://www.telefonica.com/wsdl/UNICA/SOAP/OProv_Subscriptions/v1/local\" xmlns:ns3=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/4\" xmlns:ns4=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/5\" xmlns:ns5=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/\" xmlns:ns6=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/1\" xmlns:ns7=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions_video/v2/\" xmlns:ns8=\"http://www.telefonica.com/wsdl/UNICA/SOAP/common/v1/faults\">");
			xmlString.append("         <ns2:userId>");
			xmlString.append("            <alias>" + userVo.getSubscreberId() + "</alias>");
			xmlString.append("         </ns2:userId>");
			xmlString.append("         <ns2:serviceId>10</ns2:serviceId>");
			xmlString.append("      </ns2:activateService>");
			xmlString.append("   </soap:Body>");
			xmlString.append("</soap:Envelope>");

			StringEntity userEntity = new StringEntity(
					String.valueOf(xmlString));
			post.setEntity(userEntity);

			// Executa a acao
			HttpResponse httpResponse = httpClient.execute(post);
			HttpEntity responseEntity = httpResponse.getEntity();

			String responseString = EntityUtils.toString(responseEntity,
					"UTF-8");
			System.out.println(String.valueOf(responseString));

			// Converto a String em XML
			Document document = convertStringToDocument(responseString);

			// Varre os campos do XML de retorno
			if (document.hasChildNodes()) {
				document.getDocumentElement().normalize();
				responseService = printNote(document.getChildNodes(), "");
			}

			EntityUtils.consume(responseEntity);

			if ("".equalsIgnoreCase(responseService)) {
				// responseService = "Criado globalUserID = "
				// + userVo.getSubscreberId() + " - Status = Ativo";
				logger.info("\n ===================================\n");
				logger.info("globalUserID: " + userVo.getSubscreberId());
				logger.info("Status: Ativo");
				logger.info("\n ===================================\n");
				userVo.setError(false);
			} else {
				responseService = "Criado globalUserID: "
						+ userVo.getSubscreberId()
						+ " - erro ao ativar usuário: " + responseService;
				logger.info("Criado globalUserID = " + userVo.getSubscreberId()
						+ " - erro ao ativar usuário: " + responseService);
				userVo.setError(true);
			}

			userVo.setRetorno(responseService);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVo;
	}

	@Override
	public UserVO updateService(UserVO userVo) throws ClientException,
			ServerException {
		try {
			String responseService = null;
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
				public void verify(String host, SSLSocket ssl)
						throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert)
						throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns,
						String[] subjectAlts) throws SSLException {
				}

				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});

			HttpClient httpClient = httpClientBuilder.build();
			HttpPost post = new HttpPost(URL_SERVICE);
			post.addHeader("Content-Type", "text/xml; charset=UTF-8");
			post.addHeader("Accept", "text/xml, multipart/related");
			post.addHeader("User-Agent", "JAX-WS RI 2.2.7-b01");

			StringBuilder xmlString = new StringBuilder();
			xmlString.append("<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soapenc=\"http://schemas.xmlsoap.org/soap/encoding/\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">");
			xmlString.append("   <soap:Header>");
			xmlString.append("      <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">");
			xmlString.append("         <wsse:UsernameToken wsu:Id=\"UsernameToken-2\">");
			xmlString.append("            <wsse:Username>gvp_app</wsse:Username>");
			xmlString.append("            <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">gvp_123456</wsse:Password>");
			xmlString.append("         </wsse:UsernameToken>");
			xmlString.append("      </wsse:Security>");
			xmlString.append("      <Action xmlns=\"http://www.w3.org/2005/08/addressing\">urn:updateService</Action>");
			xmlString.append("      <To xmlns=\"http://www.w3.org/2005/08/addressing\">http://telefonica.com/GVP/OTT/Provisioning/OProv_Subscriptions</To>");
			xmlString.append("      <From xmlns=\"http://www.w3.org/2005/08/addressing\">");
			xmlString.append("         <Address>http://telefonica.com/Brazil/GVT/Provisioning/OProv_Subscriptions</Address>");
			xmlString.append("      </From>");
			xmlString.append("   </soap:Header>");
			xmlString.append("   <soap:Body>");
			xmlString.append("      <ns2:updateService xmlns=\"http://www.telefonica.com/schemas/UNICA/SOAP/common/v1\" xmlns:ns2=\"http://www.telefonica.com/wsdl/UNICA/SOAP/OProv_Subscriptions/v1/local\" xmlns:ns3=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/4\" xmlns:ns4=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/5\" xmlns:ns5=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/\" xmlns:ns6=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions/v1/1\" xmlns:ns7=\"http://www.telefonica.com/schemas/UNICA/SOAP/OProv_Subscriptions_video/v2/\" xmlns:ns8=\"http://www.telefonica.com/wsdl/UNICA/SOAP/common/v1/faults\">");
			xmlString.append("         <ns2:userId>");
			xmlString.append("            <alias>" + userVo.getSubscreberId() + "</alias>");
			xmlString.append("         </ns2:userId>");
			xmlString.append("         <ns2:serviceId>10</ns2:serviceId>");
			xmlString.append("         <ns2:productPurchaseInfo>");
			xmlString.append("            <ns4:isIncremental>true</ns4:isIncremental>");
			xmlString.append("            <ns4:ProductList>");
			xmlString.append("               <ns4:product>");
			xmlString.append("                  <ns4:productId>745</ns4:productId>");
			xmlString.append("                  <ns4:productClass>Subscription</ns4:productClass>");
			xmlString.append("                  <ns4:status>On</ns4:status>");
			xmlString.append("               </ns4:product>");
			xmlString.append("               <ns4:product>");
			xmlString.append("                  <ns4:productId>746</ns4:productId>");
			xmlString.append("                  <ns4:productClass>Subscription</ns4:productClass>");
			xmlString.append("                  <ns4:status>On</ns4:status>");
			xmlString.append("               </ns4:product>");
			xmlString.append("               <ns4:product>");
			xmlString.append("                  <ns4:productId>748</ns4:productId>");
			xmlString.append("                  <ns4:productClass>Subscription</ns4:productClass>");
			xmlString.append("                  <ns4:status>On</ns4:status>");
			xmlString.append("               </ns4:product>");
			xmlString.append("            </ns4:ProductList>");
			xmlString.append("         </ns2:productPurchaseInfo>");
			xmlString.append("      </ns2:updateService>");
			xmlString.append("   </soap:Body>");
			xmlString.append("</soap:Envelope>");

			StringEntity userEntity = new StringEntity(
					String.valueOf(xmlString));
			post.setEntity(userEntity);

			// Executa a acao
			HttpResponse httpResponse = httpClient.execute(post);
			HttpEntity responseEntity = httpResponse.getEntity();

			String responseString = EntityUtils.toString(responseEntity,
					"UTF-8");
			System.out.println(String.valueOf(responseString));

			// Converto a String em XML
			Document document = convertStringToDocument(responseString);

			// Varre os campos do XML de retorno
			if (document.hasChildNodes()) {
				document.getDocumentElement().normalize();
				responseService = printNote(document.getChildNodes(), "");
			}

			EntityUtils.consume(responseEntity);
			
			if ("".equalsIgnoreCase(responseService)) {
				responseService = "Criado globalUserID: "
						+ userVo.getSubscreberId()
						+ " - Status: Ativo - Produtos: Ativos";
				logger.info("\n ===================================\n");
				logger.info("globalUserID: " + userVo.getSubscreberId());
				logger.info("Produtos: Ativos");
				logger.info("\n ===================================\n");
				userVo.setError(false);
			} else {
				responseService = "Criado globalUserID = "
						+ userVo.getSubscreberId()
						+ " Status = Ativo - erro ao ativar produtos: " + responseService;
				logger.info("Criado globalUserID = " + userVo.getSubscreberId()
						+ " Status = Ativo - erro ao ativar usuário: " + responseService);
				userVo.setError(true);
			}

			userVo.setRetorno(responseService);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userVo;
	}

	/**
	 * @param xmlStr
	 * @return Document
	 */
	private static Document convertStringToDocument(String xmlStr) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(
					xmlStr)));
			doc.getDocumentElement().normalize();
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param nodeList
	 * @param retorno
	 * @return String
	 */
	private static String printNote(NodeList nodeList, String retorno) {

		for (int count = 0; count < nodeList.getLength(); count++) {
			Node tempNode = nodeList.item(count);

			// make sure it's element node.
			if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
				// get node name and value
				// System.out.println("\nNode Name =" + tempNode.getNodeName()
				// + " [OPEN]");
				// System.out.println("Node Value =" +
				// tempNode.getTextContent());

				if (tempNode.getNodeName().equalsIgnoreCase("v1:text")
						|| tempNode.getNodeName().equalsIgnoreCase(
								"v1:variables")) {
					System.out.println("Node Value = "
							+ tempNode.getTextContent());
					if (!"".equalsIgnoreCase(retornoXml)) {
						retornoXml += " - " + tempNode.getTextContent();
					} else {
						retornoXml = tempNode.getTextContent();
					}
				}

				// if (tempNode.hasAttributes()) {
				// // get attributes names and values
				// NamedNodeMap nodeMap = tempNode.getAttributes();
				//
				// for (int i = 0; i < nodeMap.getLength(); i++) {
				// Node node = nodeMap.item(i);
				// System.out.println("attr name : " + node.getNodeName());
				// System.out.println("attr value : "
				// + node.getNodeValue());
				// }
				// }

				if (tempNode.hasChildNodes()) {
					// loop again if has child nodes
					printNote(tempNode.getChildNodes(), retornoXml);
				}

				// System.out.println("Node Name =" + tempNode.getNodeName()
				// + " [CLOSE]");
			}
		}
		return retornoXml;
	}
}