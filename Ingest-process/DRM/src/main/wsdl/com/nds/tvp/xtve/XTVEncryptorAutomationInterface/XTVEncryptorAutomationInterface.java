/**
 * XTVEncryptorAutomationInterface.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.nds.tvp.xtve.XTVEncryptorAutomationInterface;

public interface XTVEncryptorAutomationInterface extends java.rmi.Remote {
	
	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut registerClient(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault;

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.SystemInformationOut systemInformation(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.Feature[] parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault;

	public void unregisterClient(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.UnregisterClientIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault;

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut encryptContent(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault;

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut heartbeat(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault;

	public com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut abortEncryption(
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionIn parameters)
			throws java.rmi.RemoteException,
			com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AIFault;
}
