package br.com.gvt.watchfolder.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import br.com.gvt.watchfolder.util.Util;

/**
 * @author GVT
 * 
 */
public class FileFunctions {

	static Logger logger = Logger.getLogger(FileFunctions.class.getName());

	/**
	 * @param file
	 * @return xml
	 */
	public StringBuffer readXmlFileContent(File file) {
		logger.info("lendo o arquivo xml " + file.getName());
		String sCurrentLine;
		StringBuffer xml = null;
		BufferedReader br = null;
		xml = new StringBuffer();

		try {
			br = new BufferedReader(new FileReader(file));
			while ((sCurrentLine = br.readLine()) != null) {
				xml.append(sCurrentLine);
			}
		} catch (Exception e) {
			logger.error("Erro ao ler arquivo - " + file.getName(), e);
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
		logger.info("Buscando os arquivos para importe!");
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<File> filtered = new ArrayList<File>();

		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();

					if (files.toLowerCase().endsWith(extension.toLowerCase())) {
						System.out.println(files);
						logger.info("Arquivo: " + files);
						filtered.add(listOfFiles[i]);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro ao ler arquivos xml - " + e);
		}
		return filtered;
	}

	/**
	 * @param path
	 * @return
	 */
	public List<File> getXmlFromPath(String path) {
		logger.info("Buscando os arquivos para importe!");
		String files;
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		List<File> xmls = new ArrayList<File>();

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
	 * Verifica se todos os arquivos foram copiados corretamente para a pasta
	 * origem
	 * 
	 * @param pathfile
	 * @param listValidateFiles
	 * @return boolean
	 */
	public boolean verifyMovieFilesFromPath(String pathfile,
			ArrayList<String> listValidateFiles) {
		logger.info("Verifica se os arquivos no caminho " + pathfile
				+ " estao OK!");
		String files;
		File folder = new File(pathfile);
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> filesTS = new ArrayList<String>();
		boolean filesOk = false;
		long oldFileSize = 0;
		long currentFileSize = 0;
		Pattern p = Pattern.compile(Util.getWFFiles());
		try {
			for (int i = 0; i < listOfFiles.length; i++) {
				oldFileSize = 0;
				currentFileSize = 0;
				if (listOfFiles[i].isFile()) {
					files = listOfFiles[i].getName();
					Matcher m = p.matcher(files.toLowerCase());
					if (m.matches()) {
						System.out
								.println("Arquivo encontrado para validacao: "
										+ files);
						logger.info("Arquivo encontrado para validacao: "
								+ files);
						oldFileSize = listOfFiles[i].length();
						Thread.sleep(300);
						currentFileSize = listOfFiles[i].length();

						// Verifica se o arquivo nao esta sendo copiado
						if (oldFileSize == currentFileSize) {
							logger.info("Arquivo [" + listOfFiles[i].getName()
									+ "] ja copiado para FTP.");
							logger.info("Arquivo [" + listOfFiles[i].getName()
									+ "] validado.");
							filesTS.add(listOfFiles[i].getName());
						} else {
							logger.info("Arquivo [" + listOfFiles[i].getName()
									+ "] sendo copiado ainda.");
						}
					}
				}
			}

			// Ordena as listas
			Collections.sort(listValidateFiles);
			Collections.sort(filesTS);

			logger.info("listValidateFiles: " + listValidateFiles.size()
					+ " - filesTS: " + filesTS.size());
			System.out.println("listValidateFiles: " + listValidateFiles.size()
					+ " - filesTS: " + filesTS.size());

			System.out.println("listValidateFiles: "
					+ listValidateFiles.toString().toLowerCase());
			System.out.println("filesTS: " + filesTS.toString().toLowerCase());

			logger.info("listValidateFiles: "
					+ listValidateFiles.toString().toLowerCase());
			logger.info("filesTS: " + filesTS.toString().toLowerCase());

			if (compareList(listValidateFiles, filesTS)) {
				logger.info("Arquivos OK!");
				filesOk = true;
			} else {
				logger.info("Arquivos - NOK!");
			}
		} catch (Exception e) {
			logger.error("Erro ao verificar se os arquivos ja foram copiados para pasta origem - "
					+ e);
			filesOk = false;
		}
		return filesOk;
	}

	/**
	 * Verifica se os registros sao iguais
	 * 
	 * @param listOne
	 * @param listTwo
	 * @return boolean
	 */
	private static boolean compareList(ArrayList<String> listOne,
			ArrayList<String> listTwo) {
		return listOne.toString().toLowerCase()
				.contentEquals(listTwo.toString().toLowerCase()) ? true : false;
	}

	/**
	 * @param path
	 * @return
	 */
	public List<File> searchFiles(String path) {
		logger.info("Buscando os arquivos para importe no caminho: " + path);
		List<File> filteredXML = new ArrayList<File>();
		List<File> filteredJPG = new ArrayList<File>();
		File root = new File(path);
		File[] list = root.listFiles();

		try {
			for (File f : list) {
				if (f.isDirectory()) {
					logger.info("Verificando arquivo no caminho: "
							+ f.getAbsolutePath());
					searchFiles(f.getAbsolutePath());
				} else {
					if (f.getName().toLowerCase().endsWith("xml")) {
						filteredXML.add(f);
					} else if (f.getName().toLowerCase().endsWith("jpg")) {
						filteredJPG.add(f);
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
	public boolean renameXML(File file, String status) {
		File newFileName = new File(file.getAbsolutePath() + status);
		logger.info("Renomeando arquivo " + file.getName()
				+ " lido com sucesso!");
		try {
			FileUtils.moveFile(file, newFileName);
			return true;
		} catch (IOException e) {
			logger.error("Erro ao renomear arquivo " + file.getName() + " - "
					+ e);
		}
		return false;
	}

	/**
	 * @param file
	 * @return boolean
	 */
	public boolean renameXMLToXMLError(File file) {
		File newFileName = new File(file.getAbsolutePath() + ".error");
		logger.info("Renomeando arquivo " + file.getName() + " lido com erro!");
		try {
			FileUtils.moveFile(file, newFileName);
			return true;
		} catch (IOException e) {
			logger.error("Erro ao renomear arquivo " + file.getName() + " - "
					+ e);
		}
		return false;
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
	 * @param command
	 * @return String
	 */
	public String executeCommand(String command) {
		logger.info("Acessou o executeCommand: " + command);
		StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					p.getInputStream()));

			String line = "";
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
		} catch (Exception e) {
			logger.error("Erro ao executar comandos: ", e);
			e.printStackTrace();
		}
		return output.toString();
	}

	/**
	 * @param novoDiretorio
	 */
	public void createDirectory(String nameDirectory) {
		String newDirectory = null;
		String separador = java.io.File.separator;
		try {
			newDirectory = Util.getPathFile() + separador + newDirectory;
			// Verifica se o diretorio existe.
			if (!new File(newDirectory).exists()) {
				// Cria o diretório
				(new File(newDirectory)).mkdir();
			}
		} catch (Exception e) {
			logger.error("Erro ao criar o diretorio: ", e);
		}
	}
}