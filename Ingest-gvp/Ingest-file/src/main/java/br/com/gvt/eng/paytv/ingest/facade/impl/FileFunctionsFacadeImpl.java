package br.com.gvt.eng.paytv.ingest.facade.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import br.com.gvt.eng.paytv.ingest.facade.FileFunctionsFacade;
import br.com.gvt.eng.paytv.ingest.util.Util;

public class FileFunctionsFacadeImpl implements FileFunctionsFacade {

	Logger logger = Logger.getLogger(FileFunctionsFacadeImpl.class.getName());

	private List<File> filteredFiles = null;

	public FileFunctionsFacadeImpl() {
		this.filteredFiles = new ArrayList<File>();
	}

	@Override
	public List<File> searchFiles(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		long oldFileSize = 0;
		long currentFileSize = 0;
		boolean isRead = false;
		Pattern p = Pattern.compile(Util.getTypeInFiles());
		try {
			for (File f : list) {
				oldFileSize = 0;
				currentFileSize = 0;
				if (f.isDirectory()) {
					logger.info("Verificando arquivo no caminho: "
							+ f.getAbsolutePath());
					// Verifica se o diretório já foi lido
					isRead = searchFileGvp(f.getAbsolutePath());
					if (!isRead) {
						this.filteredFiles = searchFiles(f.getAbsolutePath());
					} else {
						System.out.println("O diretorio " + f.getAbsolutePath()
								+ " ja foi lido");
						logger.info("O diretorio " + f.getAbsolutePath()
								+ " ja foi lido");
					}
				} else {
					// Verifica se o diretório já foi lido
					isRead = searchFileGvp(f.getParent());
					if (isRead) {
						System.out.println("O diretorio " + f.getParent()
								+ " ja foi lido");
						logger.info("O diretorio " + f.getParent()
								+ " ja foi lido");
						break;
					} else {
						Matcher m = p.matcher(f.getName().toLowerCase());
						if (m.matches()) {
							System.out
									.println("Arquivo encontrado para validacao: "
											+ f.getName());
							logger.info("Arquivo encontrado para validacao: "
									+ f.getName());

							oldFileSize = f.length();
							// Espera 3.5 segundos
							Thread.sleep(3500);
							currentFileSize = f.length();

							// Verifica se o arquivo nao esta sendo copiado
							if (oldFileSize == currentFileSize
									&& currentFileSize > 0) {
								logger.info("Arquivo [" + f.getName()
										+ "] validado.");
								logger.info("Tamanho: [" + currentFileSize
										+ "]");
								this.filteredFiles.add(f);
							} else {
								logger.info("Arquivo [" + f.getName()
										+ "] sendo copiado ainda.");
							}
						}
					}
				}
			}
			// Ordena a lista de arquivos
			Collections.sort(this.filteredFiles);
		} catch (Exception e) {
			logger.error("Erro buscar arquivos - " + e);
		}
		return this.filteredFiles;
	}

	@Override
	public boolean copyFiles(Path pathFrom, Path pathTo) throws IOException {
		boolean valueReturn = false;
		try {
			// overwrite existing file, if exists
			CopyOption[] options = new CopyOption[] {
					StandardCopyOption.REPLACE_EXISTING,
					StandardCopyOption.COPY_ATTRIBUTES };

			if (Files.isDirectory(pathFrom)) {
				System.out.println("Criando o diretorio [" + pathTo + "]");
				logger.info("Criando o diretorio [" + pathTo + "]");

				// Criando o novo diretorio
				Files.createDirectories(pathTo);

				// listamos todas as entradas do diretório
				DirectoryStream<Path> entradas = Files
						.newDirectoryStream(pathFrom);

				for (Path entrada : entradas) {
					// para cada entrada, verifica o arquivo equivalente dentro
					// de cada arvore
					Path novaOrigem = pathFrom.resolve(entrada.getFileName());
					Path novoDestino = pathTo.resolve(entrada.getFileName());

					// invoca o metodo de maneira recursiva
					copyFiles(novaOrigem, novoDestino);
				}
			} else {
				Pattern p = Pattern.compile(Util.getTypeInFiles());
				Matcher m = p.matcher(pathFrom.getFileName().toString()
						.toLowerCase());
				if (m.matches()) {
					System.out.println("Copiando os arquivos da pasta ["
							+ pathFrom + "] para a pasta [" + pathTo + "]");
					logger.info("Copiando os arquivos da pasta [" + pathFrom
							+ "] para a pasta [" + pathTo + "]");
					Files.copy(pathFrom, pathTo, options);
				}
			}
			valueReturn = true;
		} catch (Exception e) {
			logger.error("Erro ao copiar arquivos - " + e);
		}
		return valueReturn;
	}

	@Override
	public void remameFileToDone(List<File> listFiles) throws IOException {
		File newFileName = null;
		for (File file : listFiles) {
			try {
				newFileName = new File(file.getAbsolutePath() + ".done");
				logger.info("Renomeando arquivo " + file.getName()
						+ " lido com sucesso!");
				FileUtils.moveFile(file, newFileName);
			} catch (Exception e) {
				logger.error("Erro ao renomear arquivo " + file.getName()
						+ " - " + e);
			}
		}
	}

	@Override
	public boolean createFileReadOk(String pathFile) throws IOException {
		boolean criado = false;
		try {
			File arquivo = new File(pathFile + File.separator + "read.gvp");
			// Cria arquivo se ele não existe
			criado = arquivo.createNewFile();
			if (criado) {
				// Arquivo foi criado com sucesso
				logger.info("Arquivo " + arquivo.getAbsolutePath()
						+ " foi criado com sucesso!");
			} else {
				// Arquivo não foi criado
				logger.info("Arquivo " + arquivo.getAbsolutePath()
						+ " nao foi criado!");
			}
		} catch (Exception e) {
			logger.error("Erro ao criar arquivo Read.gvp no caminho "
					+ pathFile + " - " + e);
		}
		return criado;
	}

	@Override
	public boolean searchFileGvp(String path) {
		File root = new File(path);
		File[] list = root.listFiles();
		boolean filteredFile = false;
		Pattern p = Pattern.compile(Util.getGvpFile());
		try {
			for (File f : list) {
				if (f.isDirectory()) {
					// logger.info("Verificando arquivo no caminho: "
					// + f.getAbsolutePath());
					// searchFileGvp(f.getAbsolutePath());
				} else {
					Matcher m = p.matcher(f.getName().toLowerCase());
					if (m.matches()) {
						System.out.println("Arquivo " + f.getName()
								+ " encontrado");
						logger.info("Arquivo " + f.getName() + " encontrado");
						filteredFile = true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro buscar arquivo GVP no caminho " + path + " - "
					+ e);
		}
		return filteredFile;
	}
}