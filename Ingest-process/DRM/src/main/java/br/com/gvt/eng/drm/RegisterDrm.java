package br.com.gvt.eng.drm;

import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface;

public interface RegisterDrm {

	/**
	 * Cria o registro do cliente no DRM
	 * 
	 * @param encryptorInterface
	 * @return RegisterClientOut
	 */
	public abstract RegisterClientOut registerClient(
			XTVEncryptorAutomationInterface encryptorInterface);

	/**
	 * Remove o registro do cliente do DRM
	 * 
	 * @param encryptorInterface
	 * @param registerClientOut
	 */
	public abstract void unRegisterClient(
			XTVEncryptorAutomationInterface encryptorInterface,
			RegisterClientOut registerClientOut);

	/**
	 * Remove o registro do cliente do DRM
	 * 
	 * @param cookieDrm
	 */
	public abstract void unRegisterClientByCookie(String cookieDrm);

}
