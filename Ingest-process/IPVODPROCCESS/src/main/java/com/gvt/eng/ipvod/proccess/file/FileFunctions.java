package com.gvt.eng.ipvod.proccess.file;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.imageio.stream.MemoryCacheImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.gvt.eng.ipvod.proccess.util.Util;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * @author GVT
 * 
 */
public class FileFunctions {

	Logger logger = Logger.getLogger(FileFunctions.class.getName());

	public static int DEFAULT_WIDTH = 140;

	public static int DEFAULT_HEIGH = 205;

	private final static Charset ENCODING = StandardCharsets.ISO_8859_1;

	/**
	 * @param file
	 * @return xml
	 */
	public StringBuffer readXmlFileContent(File file) {
		String sCurrentLine;
		StringBuffer xml = null;
		BufferedReader br = null;
		xml = new StringBuffer();
		logger.info("lendo os arquivos!");

		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					file), ENCODING));
			while ((sCurrentLine = br.readLine()) != null) {
				xml.append(sCurrentLine);
			}
		} catch (Exception e) {
			logger.error("Erro ao ler arquivos - " + e);
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				logger.error("Erro ao fechar o buffer - " + e);
			}
		}
		return xml;
	}

	/**
	 * @param path
	 * @param extension
	 * @return
	 */
	public List<File> getFilesFromPathByExtension(String path, String extension) {
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<File> filtered = new ArrayList<File>();
		logger.info("Buscando os arquivos no path [" + path
				+ "] pela extensao [" + extension + "]");
		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					if (files.toLowerCase().endsWith(extension.toLowerCase())) {
						System.out.println(files);
						logger.info("Arquivo[" + files + "]");
						filtered.add(listOfFiles[i]);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao ler arquivos - " + e);
		}
		return filtered;
	}

	/**
	 * @param path
	 * @return
	 */
	public List<File> getXmlFromPath(String path) {
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<File> xmls = new ArrayList<File>();
		logger.info("Buscando os arquivos para importe!");

		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();

					if (files.toLowerCase().endsWith("xml")) {
						xmls.add(listOfFiles[i]);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao ler arquivos xml - " + e);
		}
		return xmls;
	}

	/**
	 * @param path
	 * @return
	 */
	public List<File> searchFiles(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		List<File> filteredXML = new ArrayList<File>();
		try {
			for (File f : list) {
				if (f.isDirectory()) {
					logger.info("Verificando arquivo no caminho: "
							+ f.getAbsolutePath());
					searchFiles(f.getAbsolutePath());
				} else {
					if (f.getName().toLowerCase().endsWith("xml")) {
						filteredXML.add(f);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro buscar arquivos - " + e);
		}
		return filteredXML;
	}

	/**
	 * @param file
	 * @return boolean
	 */
	public boolean renameXMLToXMLDone(File file) {
		File newFileName = new File(file.getAbsolutePath() + ".done");
		boolean isRename = false;
		logger.info("Renomeando arquivo " + file.getName()
				+ " lido com sucesso!");
		try {
			FileUtils.moveFile(file, newFileName);
			isRename = true;
		} catch (IOException e) {
			logger.error("Erro ao renomear arquivo " + file.getName() + " - "
					+ e);
		}
		return isRename;
	}

	/**
	 * @param file
	 * @return boolean
	 */
	public boolean renameXMLToXMLError(File file) {
		File newFileName = new File(file.getAbsolutePath() + ".error");
		boolean isRename = false;
		logger.info("Renomeando arquivo " + file.getName() + " lido com erro!");
		try {
			FileUtils.moveFile(file, newFileName);
			isRename = true;
		} catch (IOException e) {
			logger.error("Erro ao renomear arquivo " + file.getName() + " - "
					+ e);
		}
		return isRename;
	}

	/**
	 * Copies files to new folder
	 * 
	 * @param pathImage
	 * @throws IOException
	 */
	public void copyJPGFiles(String assetId, String pathForImages)
			throws IOException {
		List<File> filteredJPG = new ArrayList<File>();
		filteredJPG = getFilesFromPathByExtension(pathForImages, "jpg");
		for (File file : filteredJPG) {
			resizeImage(file);
			// moveJPGFiles(file, assetId.replaceAll("[^a-zA-ZZ-Z1-9_]", "")
			// .replaceAll(" ", "_").trim());
			moveJPGFiles(file, assetId.trim());
			copyJPGFilesToCluster(file);
			renameJPGToJPGDone(file);
		}
	}

	/**
	 * @param file
	 * @param assetId
	 */
	private void moveJPGFiles(File file, String assetId) {
		File fileMoved = new File(Util.getPathImportImg() + assetId + "/"
				+ file.getName());
		try {
			System.out.println("Copy file: " + fileMoved.getName());
			logger.info("Copiando arquivo de imagem " + file.getName()
					+ " - path " + Util.getPathImportImg() + assetId);
			FileUtils.copyFile(file, fileMoved);
		} catch (IOException e) {
			logger.error("Erro ao copiar arquivo " + file.getName() + " - " + e);
		}
	}

	/**
	 * @param file
	 */
	private void renameJPGToJPGDone(File file) {
		File newFileName = new File(file.getAbsolutePath() + ".done");
		try {
			logger.info("Remoneando arquivo de imagem " + file.getName() + "!");
			FileUtils.moveFile(file, newFileName);
		} catch (IOException e) {
			logger.error("Erro ao renomear arquivo de imagem " + file.getName()
					+ " - " + e);
		}
	}

	/**
	 * @param file
	 * @param packageId
	 * @throws IOException
	 */
	private void copyJPGFilesToCluster(File file) throws IOException {
		Session session = null;
		ChannelSftp sftpChannel = null;
		JSch jsch = null;
		FileInputStream fi = null;
		try {
			if (!file.exists())
				throw new RuntimeException("Error. Local file not found");

			// Lista os hosts para que o arquivo seja enviado
			for (String host : Util.getScpHost()) {

				logger.info("Copiando arquivo de imagem " + file.getName()
						+ " - path " + Util.getPathClusterImg() + " - host "
						+ host);

				jsch = new JSch();
				session = jsch.getSession(Util.getScpUser().toString(),
						host.toString(), Util.getScpPort());
				session.setPassword(Util.getScpPassword());
				session.setConfig("StrictHostKeyChecking", "no");
				session.connect();

				Channel channel = session.openChannel("sftp");
				channel.connect();
				sftpChannel = (ChannelSftp) channel;
				sftpChannel.cd(Util.getPathClusterImg());

				fi = new FileInputStream(file);
				sftpChannel.put(fi, file.getName());

				if (sftpChannel != null) {
					sftpChannel.disconnect();
				}
				if (session != null) {
					session.disconnect();
				}
				if (fi != null) {
					fi.close();
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao enviar arquivo de imagem " + file.getName()
					+ " para o cluster - " + e);
		} finally {
			if (sftpChannel != null) {
				sftpChannel.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
			if (fi != null) {
				fi.close();
			}
		}
	}

	/**
	 * @param file
	 * @param error
	 */
	public void createFileError(File file, List<String> error) {
		File dir = new File(file.getParent());
		File arq = new File(dir, file.getParentFile().getName() + ".error");
		try {
			arq.createNewFile();
			FileWriter fileWriter = new FileWriter(arq, false);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			for (String string : error) {
				printWriter.println(string.toString());
			}

			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			logger.error("Erro ao criar arquivo de erro " + file.getName()
					+ " - " + e.getMessage());
		}
	}

	/**
	 * Modifica o tamanho da imagem antes de mover para os servidores
	 * 
	 * @param file
	 * @throws IOException
	 */
	public void resizeImage(File file) throws IOException {
		BufferedImage image = ImageIO.read(new File(file.getPath()));
		if (image == null) {
			System.out.println("Erro Image IO:" + file.getPath());
			logger.info("Erro Image IO:" + file.getPath());
			return;
		}
		BufferedImage scaled = getScaledInstance(image, DEFAULT_WIDTH,
				DEFAULT_HEIGH, RenderingHints.VALUE_INTERPOLATION_BILINEAR,
				true);
		writeJPG(scaled, new FileOutputStream(file.getPath()), 0.85f);
		logger.info("Alterado o tamanho da imagem [" + file.getName() + "]");
	}

	/**
	 * Modifica a escala das imagens
	 * 
	 * @param img
	 * @param targetWidth
	 * @param targetHeight
	 * @param hint
	 * @param higherQuality
	 * @return BufferedImage
	 */
	public BufferedImage getScaledInstance(BufferedImage img, int targetWidth,
			int targetHeight, Object hint, boolean higherQuality) {
		int type = (img.getTransparency() == Transparency.OPAQUE) ? BufferedImage.TYPE_INT_RGB
				: BufferedImage.TYPE_INT_ARGB;
		BufferedImage ret = (BufferedImage) img;
		int w, h;
		if (higherQuality) {
			w = img.getWidth();
			h = img.getHeight();
		} else {
			w = targetWidth;
			h = targetHeight;
		}

		// diminuir a imagem diversas vezes para não perder qualidade
		do {
			if (higherQuality && w > targetWidth) {
				w /= 2;
				if (w < targetWidth) {
					w = targetWidth;
				}
			}

			if (higherQuality && h > targetHeight) {
				h /= 2;
				if (h < targetHeight) {
					h = targetHeight;
				}
			}

			BufferedImage tmp = new BufferedImage(w, h, type);
			Graphics2D g2 = tmp.createGraphics();
			g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, hint);
			g2.drawImage(ret, 0, 0, w, h, null);
			g2.dispose();
			ret.flush();
			ret = tmp;
		} while (w != targetWidth || h != targetHeight);

		return ret;
	}

	/**
	 * Grava a imagem com as novas escalas
	 * 
	 * @param bufferedImage
	 * @param outputStream
	 * @param quality
	 * @throws IOException
	 */
	public void writeJPG(BufferedImage bufferedImage,
			OutputStream outputStream, float quality) throws IOException {
		Iterator<ImageWriter> iterator = ImageIO
				.getImageWritersByFormatName("jpg");
		ImageWriter imageWriter = iterator.next();
		ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
		imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
		imageWriteParam.setCompressionQuality(quality);
		ImageOutputStream imageOutputStream = new MemoryCacheImageOutputStream(
				outputStream);
		imageWriter.setOutput(imageOutputStream);
		IIOImage iioimage = new IIOImage(bufferedImage, null, null);
		imageWriter.write(null, iioimage, imageWriteParam);
		imageOutputStream.flush();
	}
}
