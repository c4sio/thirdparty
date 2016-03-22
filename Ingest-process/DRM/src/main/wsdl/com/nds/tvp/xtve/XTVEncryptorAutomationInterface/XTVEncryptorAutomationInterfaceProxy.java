package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class XTVEncryptorAutomationInterfaceProxy
		implements
		com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface {
	private String _endpoint = null;
	private com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface xTVEncryptorAutomationInterface = null;

	public XTVEncryptorAutomationInterfaceProxy() {
		_initXTVEncryptorAutomationInterfaceProxy();
	}

	public XTVEncryptorAutomationInterfaceProxy(String endpoint) {
		_endpoint = endpoint;
		_initXTVEncryptorAutomationInterfaceProxy();
	}

	private void _initXTVEncryptorAutomationInterfaceProxy() {
		try {
			xTVEncryptorAutomationInterface = (new com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEAutomationInterfaceLocator())
					.getXTVEAutomationInterface();
			if (xTVEncryptorAutomationInterface != null) {
				if (_endpoint != null)
					((javax.xml.rpc.Stub) xTVEncryptorAutomationInterface)
							._setProperty(
									"javax.xml.rpc.service.endpoint.address",
									_endpoint);
				else
					_endpoint = (String) ((javax.xml.rpc.Stub) xTVEncryptorAutomationInterface)
							._getProperty("javax.xml.rpc.service.endpoint.address");
			}

		} catch (javax.xml.rpc.ServiceException serviceException) {
		}
	}

	public String getEndpoint() {
		return _endpoint;
	}

	public void setEndpoint(String endpoint) {
		_endpoint = endpoint;
		if (xTVEncryptorAutomationInterface != null)
			((javax.xml.rpc.Stub) xTVEncryptorAutomationInterface)
					._setProperty("javax.xml.rpc.service.endpoint.address",
							_endpoint);

	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface getXTVEncryptorAutomationInterface() {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		return xTVEncryptorAutomationInterface;
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut registerClient(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		return xTVEncryptorAutomationInterface.registerClient(parameters);
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut systemInformation(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		return xTVEncryptorAutomationInterface.systemInformation(parameters);
	}

	public void unregisterClient(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.UnregisterClientIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		xTVEncryptorAutomationInterface.unregisterClient(parameters);
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut encryptContent(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		return xTVEncryptorAutomationInterface.encryptContent(parameters);
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut heartbeat(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		return xTVEncryptorAutomationInterface.heartbeat(parameters);
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut abortEncryption(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (xTVEncryptorAutomationInterface == null)
			_initXTVEncryptorAutomationInterfaceProxy();
		return xTVEncryptorAutomationInterface.abortEncryption(parameters);
	}

}