/**
 * XTVEncryptorAutomationInterfaceStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public class XTVEncryptorAutomationInterfaceStub extends
		org.apache.axis.client.Stub
		implements
		com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface {
	private java.util.Vector cachedSerClasses = new java.util.Vector();
	private java.util.Vector cachedSerQNames = new java.util.Vector();
	private java.util.Vector cachedSerFactories = new java.util.Vector();
	private java.util.Vector cachedDeserFactories = new java.util.Vector();

	static org.apache.axis.description.OperationDesc[] _operations;

	static {
		_operations = new org.apache.axis.description.OperationDesc[6];
		_initOperationDesc1();
	}

	private static void _initOperationDesc1() {
		org.apache.axis.description.OperationDesc oper;
		org.apache.axis.description.ParameterDesc param;
		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("RegisterClient");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"RegisterClientIn"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">RegisterClientIn"),
				com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientIn.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">RegisterClientOut"));
		oper.setReturnClass(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut.class);
		oper.setReturnQName(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"RegisterClientOut"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AIFault"),
				"com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault",
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AIFault"), true));
		_operations[0] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("UnregisterClient");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"UnregisterClientIn"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">UnregisterClientIn"),
				com.nds.tvp.xtve.XTVEncryptorAutomationInterface.UnregisterClientIn.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AIFault"),
				"com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault",
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AIFault"), true));
		_operations[1] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("SystemInformation");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"SystemInformationIn"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">SystemInformationIn"),
				com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[].class,
				false, false);
		param.setItemQName(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"Features"));
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">SystemInformationOut"));
		oper.setReturnClass(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut.class);
		oper.setReturnQName(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"SystemInformationOut"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AIFault"),
				"com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault",
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AIFault"), true));
		_operations[2] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("EncryptContent");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"EncryptContentIn"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">EncryptContentIn"),
				com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentIn.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">EncryptContentOut"));
		oper.setReturnClass(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut.class);
		oper.setReturnQName(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"EncryptContentOut"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AIFault"),
				"com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault",
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AIFault"), true));
		_operations[3] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("Heartbeat");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"HeartbeatIn"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">HeartbeatIn"),
				com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatIn.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">HeartbeatOut"));
		oper.setReturnClass(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut.class);
		oper.setReturnQName(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"HeartbeatOut"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AIFault"),
				"com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault",
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AIFault"), true));
		_operations[4] = oper;

		oper = new org.apache.axis.description.OperationDesc();
		oper.setName("AbortEncryption");
		param = new org.apache.axis.description.ParameterDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AbortEncryptionIn"),
				org.apache.axis.description.ParameterDesc.IN,
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AbortEncryptionIn"),
				com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionIn.class,
				false, false);
		oper.addParameter(param);
		oper.setReturnType(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">AbortEncryptionOut"));
		oper.setReturnClass(com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut.class);
		oper.setReturnQName(new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"AbortEncryptionOut"));
		oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
		oper.setUse(org.apache.axis.constants.Use.LITERAL);
		oper.addFault(new org.apache.axis.description.FaultDesc(
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						"AIFault"),
				"com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault",
				new javax.xml.namespace.QName(
						"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
						">AIFault"), true));
		_operations[5] = oper;

	}

	public XTVEncryptorAutomationInterfaceStub()
			throws org.apache.axis.AxisFault {
		this(null);
	}

	public XTVEncryptorAutomationInterfaceStub(java.net.URL endpointURL,
			javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
		this(service);
		super.cachedEndpoint = endpointURL;
	}

	public XTVEncryptorAutomationInterfaceStub(javax.xml.rpc.Service service)
			throws org.apache.axis.AxisFault {
		if (service == null) {
			super.service = new org.apache.axis.client.Service();
		} else {
			super.service = service;
		}
		((org.apache.axis.client.Service) super.service)
				.setTypeMappingVersion("1.2");
		java.lang.Class cls;
		javax.xml.namespace.QName qName;
		javax.xml.namespace.QName qName2;
		java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
		java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
		java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
		java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
		java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
		java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
		java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
		java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
		java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
		java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">AbortEncryptionIn");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionIn.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">AbortEncryptionOut");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">AIFault");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">EncryptContentIn");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentIn.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">EncryptContentOut");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">HeartbeatIn");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatIn.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">HeartbeatOut");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">RegisterClientIn");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientIn.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">RegisterClientOut");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">SystemInformationIn");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"Feature");
		qName2 = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"Features");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">SystemInformationOut");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				">UnregisterClientIn");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.UnregisterClientIn.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"ClientCapacity");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientCapacity.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"ClientRegistration");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientRegistration.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"ClientRegistrations");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ClientRegistration[].class;
		cachedSerClasses.add(cls);
		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"ClientRegistration");
		qName2 = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"ClientRegistration");
		cachedSerFactories
				.add(new org.apache.axis.encoding.ser.ArraySerializerFactory(
						qName, qName2));
		cachedDeserFactories
				.add(new org.apache.axis.encoding.ser.ArrayDeserializerFactory());

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"ConnectionTime");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.ConnectionTime.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"CurrentTime");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.CurrentTime.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

		qName = new javax.xml.namespace.QName(
				"http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/",
				"Feature");
		cachedSerQNames.add(qName);
		cls = com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature.class;
		cachedSerClasses.add(cls);
		cachedSerFactories.add(beansf);
		cachedDeserFactories.add(beandf);

	}

	protected org.apache.axis.client.Call createCall()
			throws java.rmi.RemoteException {
		try {
			org.apache.axis.client.Call _call = super._createCall();
			if (super.maintainSessionSet) {
				_call.setMaintainSession(super.maintainSession);
			}
			if (super.cachedUsername != null) {
				_call.setUsername(super.cachedUsername);
			}
			if (super.cachedPassword != null) {
				_call.setPassword(super.cachedPassword);
			}
			if (super.cachedEndpoint != null) {
				_call.setTargetEndpointAddress(super.cachedEndpoint);
			}
			if (super.cachedTimeout != null) {
				_call.setTimeout(super.cachedTimeout);
			}
			if (super.cachedPortName != null) {
				_call.setPortName(super.cachedPortName);
			}
			java.util.Enumeration keys = super.cachedProperties.keys();
			while (keys.hasMoreElements()) {
				java.lang.String key = (java.lang.String) keys.nextElement();
				_call.setProperty(key, super.cachedProperties.get(key));
			}
			// All the type mapping information is registered
			// when the first call is made.
			// The type mapping information is actually registered in
			// the TypeMappingRegistry of the service, which
			// is the reason why registration is only needed for the first call.
			synchronized (this) {
				if (firstCall()) {
					// must set encoding style before registering serializers
					_call.setEncodingStyle(null);
					for (int i = 0; i < cachedSerFactories.size(); ++i) {
						java.lang.Class cls = (java.lang.Class) cachedSerClasses
								.get(i);
						javax.xml.namespace.QName qName = (javax.xml.namespace.QName) cachedSerQNames
								.get(i);
						java.lang.Object x = cachedSerFactories.get(i);
						if (x instanceof Class) {
							java.lang.Class sf = (java.lang.Class) cachedSerFactories
									.get(i);
							java.lang.Class df = (java.lang.Class) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						} else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
							org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) cachedSerFactories
									.get(i);
							org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) cachedDeserFactories
									.get(i);
							_call.registerTypeMapping(cls, qName, sf, df, false);
						}
					}
				}
			}
			return _call;
		} catch (java.lang.Throwable _t) {
			throw new org.apache.axis.AxisFault(
					"Failure trying to get the Call object", _t);
		}
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut registerClient(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[0]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/RegisterClient");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("",
				"RegisterClient"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call
					.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) {
					throw (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public void unregisterClient(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.UnregisterClientIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[1]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/UnregisterClient");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("",
				"UnregisterClient"));

		setRequestHeaders(_call);
		setAttachments(_call);
		_call.invokeOneWay(new java.lang.Object[] { parameters });

	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut systemInformation(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[2]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/SystemInformation");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("",
				"SystemInformation"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call
					.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) {
					throw (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut encryptContent(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[3]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/EncryptContent");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("",
				"EncryptContent"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call
					.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) {
					throw (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut heartbeat(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[4]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/Heartbeat");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("", "Heartbeat"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call
					.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) {
					throw (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut abortEncryption(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault {
		if (super.cachedEndpoint == null) {
			throw new org.apache.axis.NoEndPointException();
		}
		org.apache.axis.client.Call _call = createCall();
		_call.setOperation(_operations[5]);
		_call.setUseSOAPAction(true);
		_call.setSOAPActionURI("http://xtve.tvp.nds.com/XTVEncryptorAutomationInterface/AbortEncryption");
		_call.setEncodingStyle(null);
		_call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR,
				Boolean.FALSE);
		_call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS,
				Boolean.FALSE);
		_call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
		_call.setOperationName(new javax.xml.namespace.QName("",
				"AbortEncryption"));

		setRequestHeaders(_call);
		setAttachments(_call);
		try {
			java.lang.Object _resp = _call
					.invoke(new java.lang.Object[] { parameters });

			if (_resp instanceof java.rmi.RemoteException) {
				throw (java.rmi.RemoteException) _resp;
			} else {
				extractAttachments(_call);
				try {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut) _resp;
				} catch (java.lang.Exception _exception) {
					return (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut) org.apache.axis.utils.JavaUtils
							.convert(
									_resp,
									com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut.class);
				}
			}
		} catch (org.apache.axis.AxisFault axisFaultException) {
			if (axisFaultException.detail != null) {
				if (axisFaultException.detail instanceof java.rmi.RemoteException) {
					throw (java.rmi.RemoteException) axisFaultException.detail;
				}
				if (axisFaultException.detail instanceof com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) {
					throw (com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault) axisFaultException.detail;
				}
			}
			throw axisFaultException;
		}
	}

}
