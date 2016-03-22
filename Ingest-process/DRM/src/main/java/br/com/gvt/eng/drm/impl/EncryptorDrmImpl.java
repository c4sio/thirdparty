package br.com.gvt.eng.drm.impl;

import java.io.File;

import org.apache.axis.types.UnsignedByte;
import org.apache.axis.types.UnsignedLong;
import org.apache.log4j.Logger;

import br.com.gvt.eng.drm.EncryptorDrm;
import br.com.gvt.eng.drm.RegisterDrm;
import br.com.gvt.eng.drm.util.Util;
import br.com.gvt.eng.drm.vo.IpvodIngestStage;

import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionIn;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentIn;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatIn;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.RegisterClientOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterface;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.XTVEncryptorAutomationInterfaceProxy;

public class EncryptorDrmImpl implements EncryptorDrm {

	static Logger logger = Logger.getLogger(EncryptorDrmImpl.class.getName());

	@Override
	public HeartbeatOut checkStatusEncryptor(String cookiedDrm) {
		HeartbeatOut heartbeatOut = new HeartbeatOut();
		try {
			logger.info("Verificando status do arquivo no DRM - ID "
					+ cookiedDrm);
			// Seta o Endpoint dos servicos DRM
			XTVEncryptorAutomationInterface encryptorInterface = new XTVEncryptorAutomationInterfaceProxy(
					Util.getEndPointDrm());
			// Verifica
			if (cookiedDrm != null) {
				HeartbeatIn heartbeatIn = new HeartbeatIn();
				heartbeatIn.setClientCookie(new UnsignedLong(cookiedDrm));
				// Retorna valores
				heartbeatOut = encryptorInterface.heartbeat(heartbeatIn);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao verificar o status do job no DRM: ", e);
		}
		return heartbeatOut;
	}

	@Override
	public EncryptContentOut encryptFile(IpvodIngestStage ipvodIngestStage,
			File file, String type) {
		EncryptContentOut encryptContentOut = null;
		EncryptContentIn encryptContentIn = null;
		RegisterClientOut registerClientOut = null;
		RegisterDrm registerDrm = null;
		XTVEncryptorAutomationInterface encryptorInterface = null;
		try {
			logger.info("Enviando arquivo para criptografar: " + file.getName());

			// Seta o Endpoint dos servicos DRM
			encryptorInterface = new XTVEncryptorAutomationInterfaceProxy(
					Util.getEndPointDrm());

			// Requisitando o cookie
			registerDrm = new RegisterDrmImpl();
			registerClientOut = registerDrm.registerClient(encryptorInterface);
			String destLoc = Util.getOutFilesPath();
			String crid = ipvodIngestStage.getIpvodAsset().getAssetId() + "_"
					+ type;
			String title = ipvodIngestStage.getIpvodAsset().getTitle();

			String sourceLoc = Util.getSourceLocFilesPath() + File.separator
					+ ipvodIngestStage.getAssetInfo() + "_" + type
					+ File.separator + file.getName();

			if (registerClientOut.getClientCookie() != null) {
				logger.info("Criado o cookie: "
						+ registerClientOut.getClientCookie());

				System.out.println("\n\n8=====================D (  |*|  )\n\n");
				System.out.println("[setClientCookie: "
						+ registerClientOut.getClientCookie() + "]");
				System.out.println("[setJobType: " + new UnsignedByte(7) + "]");
				System.out.println("[setSourceLoc: " + sourceLoc + "]");
				System.out.println("[setDestLoc: " + destLoc + "]");
				System.out.println("[setMetadataLoc: " + destLoc + "]");
				System.out.println("[setPhysical_CRID: " + crid + "]");
				System.out.println("[setClip_CRID: " + crid + "]");
				System.out.println("[setTitle: " + title + "]");
				System.out.println("[setStreamTypes: " + new UnsignedByte(4)
						+ "]");
				System.out.println("\n\n8=====================D (  |*|  )\n\n");

				logger.info("[setClientCookie: "
						+ registerClientOut.getClientCookie() + "]");
				logger.info("[setJobType: " + new UnsignedByte(7) + "]");
				logger.info("[setSourceLoc: " + sourceLoc + "]");
				logger.info("[setDestLoc: " + destLoc + "]");
				logger.info("[setMetadataLoc: " + destLoc + "]");
				logger.info("[setPhysical_CRID: " + crid + "]");
				logger.info("[setClip_CRID: " + crid + "]");
				logger.info("[setTitle: " + title + "]");
				logger.info("[setStreamTypes: " + new UnsignedByte(4) + "]");

				// Setando as propriedades
				encryptContentIn = new EncryptContentIn();
				encryptContentIn.setClientCookie(registerClientOut
						.getClientCookie());
				encryptContentIn.setJobType(new UnsignedByte(7));
				encryptContentIn.setSourceLoc(sourceLoc);
				encryptContentIn.setDestLoc(destLoc);
				encryptContentIn.setMetadataLoc(destLoc);
				encryptContentIn.setPhysical_CRID(crid);
				encryptContentIn.setClip_CRID(crid);
				encryptContentIn.setTitle(title);
				encryptContentIn.setStreamTypes(new UnsignedByte(4));

				// Envia os dados
				encryptContentOut = encryptorInterface
						.encryptContent(encryptContentIn);
			}

			logger.info("Enviando o arquivo para o DRM");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Erro ao criptografar o arquivo no DRM: ", e);
			if (registerClientOut != null) {
				logger.info("Removendo cookie: "
						+ registerClientOut.getClientCookie());
				registerDrm.unRegisterClient(encryptorInterface,
						registerClientOut);
			}
		}
		return encryptContentOut;
	}

	@Override
	public AbortEncryptionOut AbortEncryption(String cookiedDrm,
			String jobNumber) {
		AbortEncryptionIn abortEncryptionIn = null;
		AbortEncryptionOut abortEncryption = null;
		try {
			logger.info("Efetuando o abort do processo de criptografia - cookie ["
					+ cookiedDrm + "] - jobNumber [" + jobNumber + "]");

			// Seta o Endpoint dos servicos DRM
			XTVEncryptorAutomationInterface encryptorInterface = new XTVEncryptorAutomationInterfaceProxy(
					Util.getEndPointDrm());

			// Setando as propriedades
			abortEncryptionIn = new AbortEncryptionIn();
			abortEncryptionIn.setClientCookie(new UnsignedLong(cookiedDrm));
			abortEncryptionIn.setJobNumber(new UnsignedLong(jobNumber));

			// Envia os dados
			abortEncryption = encryptorInterface
					.abortEncryption(abortEncryptionIn);
		} catch (Exception e) {
			logger.error(
					"Erro ao efetuar o abort da criptografia do arquivo no DRM: ",
					e);
		}
		return abortEncryption;
	}
}
