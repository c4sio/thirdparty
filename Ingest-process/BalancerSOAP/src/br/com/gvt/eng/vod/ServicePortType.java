package br.com.gvt.eng.vod;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Holder;

@WebService(targetNamespace = "urn:envivio:balancer:1.0", name = "ServicePortType")
@XmlSeeAlso({ ObjectFactory.class })
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface ServicePortType {

	@WebMethod(action = "urn:envivio:balancer:1.0/getAllResources")
	@WebResult(name = "resources", targetNamespace = "urn:envivio:balancer:1.0", partName = "resources")
	public Resourcelist getAllResources();

	@WebMethod(action = "urn:envivio:balancer:1.0/getJobStatus")
	public void getJobStatus(
			@WebParam(partName = "jobid", name = "jobid") java.lang.String jobid,
			@WebParam(partName = "state", mode = WebParam.Mode.OUT, name = "state") Holder<java.lang.String> state,
			@WebParam(partName = "infos", mode = WebParam.Mode.OUT, name = "infos") Holder<Jobstatusinfolist> infos,
			@WebParam(partName = "quality", mode = WebParam.Mode.OUT, name = "quality") Holder<Jobqualityres> quality);

	@WebMethod(action = "urn:envivio:balancer:1.0/getVersion")
	@WebResult(name = "version", targetNamespace = "urn:envivio:balancer:1.0", partName = "version")
	public String getVersion();

	@WebMethod(action = "urn:envivio:balancer:1.0/cancelJob")
	public void cancelJob(
			@WebParam(partName = "jobid", name = "jobid") String jobid);

	@WebMethod(action = "urn:envivio:balancer:1.0/launchJob")
	@WebResult(name = "jobid", targetNamespace = "urn:envivio:balancer:1.0", partName = "jobid")
	public String launchJob(
			@WebParam(partName = "presetid", name = "presetid") String presetid,
			@WebParam(partName = "jobparams", name = "jobparams") Params jobparams,
			@WebParam(partName = "jobname", name = "jobname") String jobname);

	@WebMethod(action = "urn:envivio:balancer:1.0/start")
	public void start();

	@WebMethod(action = "urn:envivio:balancer:1.0/getJobStatusSummary")
	@WebResult(name = "states", targetNamespace = "urn:envivio:balancer:1.0", partName = "states")
	public Jobstatesummarylist getJobStateSummary();

	@WebMethod(action = "urn:envivio:balancer:1.0/getAllQualityServers")
	@WebResult(name = "qualityservers", targetNamespace = "urn:envivio:balancer:1.0", partName = "qualityservers")
	public Qualityserverlist getAllQualityServers();

	@WebMethod(action = "urn:envivio:balancer:1.0/getStatus")
	@WebResult(name = "status", targetNamespace = "urn:envivio:balancer:1.0", partName = "status")
	public String getStatus();

	@WebMethod(action = "urn:envivio:balancer:1.0/removeResource")
	public void removeResource(
			@WebParam(partName = "resourceid", name = "resourceid") String resourceid);

	@WebMethod(action = "urn:envivio:balancer:1.0/addResource")
	@WebResult(name = "resourceid", targetNamespace = "urn:envivio:balancer:1.0", partName = "resourceid")
	public String addResource(
			@WebParam(partName = "resource", name = "resource") Resource resource);

	@WebMethod(action = "urn:envivio:balancer:1.0/stop")
	public void stop();

	@WebMethod(action = "urn:envivio:balancer:1.0/getAllPresets")
	@WebResult(name = "presets", targetNamespace = "urn:envivio:balancer:1.0", partName = "presets")
	public Presetlist getAllPresets();

	@WebMethod(action = "urn:envivio:balancer:1.0/addQualityServer")
	@WebResult(name = "qualityserverid", targetNamespace = "urn:envivio:balancer:1.0", partName = "qualityserverid")
	public String addQualityServer(
			@WebParam(partName = "qualityserver", name = "qualityserver") Qualityserver qualityserver);

	@WebMethod(action = "urn:envivio:balancer:1.0/removeQualityServer")
	public void removeQualityServer(
			@WebParam(partName = "qualityserverid", name = "qualityserverid") String qualityserverid);

	@WebMethod(action = "urn:envivio:balancer:1.0/getQSTestPlans")
	@WebResult(name = "testplans", targetNamespace = "urn:envivio:balancer:1.0", partName = "testplans")
	public Testplanlist getQSTestPlans(
			@WebParam(partName = "qualityserverid", name = "qualityserverid") String qualityserverid);
}
