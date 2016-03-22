package br.com.gvt.eng.vod.clientsample;

import br.com.gvt.eng.vod.Envivio4BalancerService;
import br.com.gvt.eng.vod.Param;
import br.com.gvt.eng.vod.Params;
import br.com.gvt.eng.vod.ServicePortType;

public class ClientSample {

	public static void main(String[] args) {
		System.out.println("***********************");
		System.out.println("Create Web Service Client...");
		Envivio4BalancerService service1 = new Envivio4BalancerService();
		System.out.println("Create Web Service...");
		ServicePortType port1 = service1.getService();
		System.out.println("Call Web Service Operation...");
		System.out.println("Server said: " + port1.getAllResources());
		System.out
				.println("Server said: port1.getJobStatus() is a void method!");
		System.out.println("Server said: " + port1.getVersion());
		System.out.println("Server said: port1.cancelJob() is a void method!");

		Param param = new Param();
		param.setName("inputfilename");
		param.setValue("file://myshare/somepath/myinputfile.ts");

		Param param1 = new Param();
		param1.setName("outputfilename");
		param1.setValue("file://myshare/somepath/myoutputfile.mp4");

		Params params = new Params();
		params.getParam().add(param);
		params.getParam().add(param1);

		System.out.println("Server said: "
				+ port1.launchJob("IPTV_HD", params, "myjob"));
		// Please input the parameters instead of 'null' for the upper method!

		System.out.println("Server said: port1.start() is a void method!");
		System.out.println("Server said: " + port1.getJobStateSummary());
		System.out.println("Server said: " + port1.getAllQualityServers());
		System.out.println("Server said: " + port1.getStatus());
		System.out
				.println("Server said: port1.removeResource() is a void method!");
//		System.out.println("Server said: " + port1.addResource(null));
		// Please input the parameters instead of 'null' for the upper method!

		System.out.println("Server said: port1.stop() is a void method!");
		System.out.println("Server said: " + port1.getAllPresets());
//		System.out.println("Server said: " + port1.addQualityServer(null));
		// Please input the parameters instead of 'null' for the upper method!

		System.out
				.println("Server said: port1.removeQualityServer() is a void method!");
//		System.out.println("Server said: " + port1.getQSTestPlans(null));
		// Please input the parameters instead of 'null' for the upper method!

//		System.out.println("Create Web Service...");
//		ServicePortType port2 = service1.getService();
//		System.out.println("Call Web Service Operation...");
//		System.out.println("Server said: " + port2.getAllResources());
//		System.out
//				.println("Server said: port2.getJobStatus() is a void method!");
//		System.out.println("Server said: " + port2.getVersion());
//		System.out.println("Server said: port2.cancelJob() is a void method!");
//		System.out.println("Server said: " + port2.launchJob(null, null, null));
		// Please input the parameters instead of 'null' for the upper method!

//		System.out.println("Server said: port2.start() is a void method!");
//		System.out.println("Server said: " + port2.getJobStateSummary());
//		System.out.println("Server said: " + port2.getAllQualityServers());
//		System.out.println("Server said: " + port2.getStatus());
//		System.out
//				.println("Server said: port2.removeResource() is a void method!");
//		System.out.println("Server said: " + port2.addResource(null));
		// Please input the parameters instead of 'null' for the upper method!

//		System.out.println("Server said: port2.stop() is a void method!");
//		System.out.println("Server said: " + port2.getAllPresets());
//		System.out.println("Server said: " + port2.addQualityServer(null));
		// Please input the parameters instead of 'null' for the upper method!

//		System.out
//				.println("Server said: port2.removeQualityServer() is a void method!");
//		System.out.println("Server said: " + port2.getQSTestPlans(null));
		// Please input the parameters instead of 'null' for the upper method!

		System.out.println("***********************");
		System.out.println("Call Over!");
	}
}
