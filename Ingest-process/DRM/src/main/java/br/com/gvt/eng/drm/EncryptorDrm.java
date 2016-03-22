package br.com.gvt.eng.drm;

import java.io.File;

import br.com.gvt.eng.drm.vo.IpvodIngestStage;

import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.AbortEncryptionOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.EncryptContentOut;
import com.nds.tvp.xtve.XTVEncryptorAutomationInterface.HeartbeatOut;

public interface EncryptorDrm {

	/**
	 * Verifica o status do Job no DRM
	 * 
	 * @param cookiedDrm
	 * @return HeartbeatOut
	 */
	public abstract HeartbeatOut checkStatusEncryptor(String cookiedDrm);

	/**
	 * Envia o arquivo para o DRM
	 * 
	 * @param ipvodIngestStage
	 * @param fileName
	 * @param file
	 * @param type
	 * @return EncryptContentOut
	 */
	public abstract EncryptContentOut encryptFile(
			IpvodIngestStage ipvodIngestStage, File file, String type);

	/**
	 * Para o processo de criptografia do arquivo
	 * 
	 * @param cookiedDrm
	 * @param jobNumber
	 * @return AbortEncryptionOut
	 */
	public abstract AbortEncryptionOut AbortEncryption(String cookiedDrm,
			String jobNumber);

}
