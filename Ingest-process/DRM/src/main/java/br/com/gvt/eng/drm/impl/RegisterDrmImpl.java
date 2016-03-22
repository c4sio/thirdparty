package br.com.gvt.eng.drm.impl;

import org.apache.axis.types.UnsignedLong;
import org.apache.log4j.Logger;

import br.com.gvt.eng.drm.RegisterDrm;
import br.com.gvt.eng.drm.util.Util;

import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientIn;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.UnregisterClientIn;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterfaceProxy;

public class RegisterDrmImpl implements RegisterDrm {

	static Logger logger = Logger.getLogger(RegisterDrmImpl.class.getName());

	@Override
	public RegisterClientOut registerClient(
			XTVEncryptorAutomationInterface encryptorInterface) {
		RegisterClientIn registerClientIn = new RegisterClientIn();
		RegisterClientOut registerClientOut = null;
		try {
			logger.info("Criando o cookie do cliente no DRM");
			registerClientOut = encryptorInterface
					.registerClient(registerClientIn);

			if (registerClientOut.getClientCookie() != null) {
				System.out.println("Cliente cookie gerado: "
						+ registerClientOut.getClientCookie());
				logger.info("Cliente cookie gerado: "
						+ registerClientOut.getClientCookie());
			}
		} catch (Exception e) {
			logger.error("Erro ao executar o registro do Cliente no DRM: ", e);
		}
		return registerClientOut;
	}

	@Override
	public void unRegisterClient(
			XTVEncryptorAutomationInterface encryptorInterface,
			RegisterClientOut registerClientOut) {
		try {
			logger.info("Removendo o registro do cliente no DRM: "
					+ registerClientOut.getClientCookie());
			// Verifica se o registro nao esta vazio
			if (registerClientOut.getClientCookie() != null) {
				UnregisterClientIn unregisterClientIn = new UnregisterClientIn(
						registerClientOut.getClientCookie());
				// Remove o cookie do DRM
				encryptorInterface.unregisterClient(unregisterClientIn);
			}
		} catch (Exception e) {
			logger.error("Erro ao remover o Cookie do Cliente no DRM: ", e);
		}
	}

	@Override
	public void unRegisterClientByCookie(String cookieDrm) {
		try {
			logger.info("Removendo o registro do cliente no DRM: " + cookieDrm);

			// Seta o Endpoint dos servicos DRM
			XTVEncryptorAutomationInterface encryptorInterface = new XTVEncryptorAutomationInterfaceProxy(
					Util.getEndPointDrm());

			// Verifica se o registro nao esta vazio
			if (cookieDrm != null) {
				UnregisterClientIn unregisterClientIn = new UnregisterClientIn(
						new UnsignedLong(cookieDrm));
				// Remove o cookie do DRM
				encryptorInterface.unregisterClient(unregisterClientIn);
			}
		} catch (Exception e) {
			logger.error("Erro ao remover o Cookie do Cliente no DRM: ", e);
		}
	}
}