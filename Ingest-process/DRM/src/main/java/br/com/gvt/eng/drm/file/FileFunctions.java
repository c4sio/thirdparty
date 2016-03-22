package br.com.gvt.eng.drm.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.log4j.Logger;

import br.com.gvt.eng.drm.util.Util;

public class FileFunctions {

	static Logger logger = Logger.getLogger(FileFunctions.class.getName());

	/**
	 * @param path
	 * @return List<File>
	 */
	public List<File> searchFiles(String path) {
		List<File> filteredFiles = new ArrayList<File>();
		try {
			File root = new File(Util.getInFilesPath() + path);
			File[] list = root.listFiles();
			logger.info("Busca arquivos na pasta: " + root.getPath());

			for (File f : list) {
				if (f.isDirectory()) {
					logger.info("Verificando arquivo no caminho: "
							+ f.getAbsolutePath());
					searchFiles(f.getAbsolutePath());
				} else {
					// Arquivos m3u8
					if (f.getName().equalsIgnoreCase("index.m3u8")) {
						logger.info("[" + f.getName() + "]");
						filteredFiles.add(f);
					}
				}
			}
		} catch (Exception e) {
			logger.error("Erro buscar arquivos - " + e);
		}

		System.out.println("Encontrou " + filteredFiles.size()
				+ " um arquivo valido no caminho " + path);
		logger.info("Encontrou " + filteredFiles.size()
				+ " um arquivo valido no caminho " + path);

		return filteredFiles;
	}

	/**
	 * Cria um diretorio na entrada Convoy com os arquivos de trailer
	 * 
	 * @param sourceDir
	 * @param destDir
	 * @throws IOException
	 */
	public void copyDirectory(File sourceDir, File destDir) throws IOException {

		logger.info("Movendo os arquivos de trailer da origem "
				+ sourceDir.getPath() + " para destino " + destDir.getPath());
		System.out.println("Movendo os arquivos de trailer da origem "
				+ sourceDir.getPath() + " para destino " + destDir.getPath());
		boolean newDir = false;

		if (!destDir.exists()) {
			logger.info("Criando o diretorio de destino " + destDir);
			System.out.println("Criando o diretorio de destino " + destDir);
			newDir = destDir.mkdir();
			logger.info("Diretorio " + destDir + " criado? " + newDir);
			System.out.print("Diretorio " + destDir + " criado? " + newDir);
		}

		File[] children = sourceDir.listFiles();

		logger.info("Encontrou " + children.length + " arquivos para mover.");

		for (File sourceChild : children) {
			String name = sourceChild.getName();
			File destChild = new File(destDir, name);
			if (sourceChild.isDirectory()) {
				logger.info("Copiando diretorio");
				copyDirectory(sourceChild, destChild);
			} else {
				// Copiando arquivos
				copyFile(sourceChild, destChild);
			}
		}
	}

	/**
	 * Copia os arquivos de trailer na pasta de entrada do Convoy
	 * 
	 * @param source
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFile(File source, File dest) throws IOException {

		if (!dest.exists()) {
			logger.info("Criando arquivo [" + dest.getName() + "] no destino");
			dest.createNewFile();
		}

		InputStream in = null;
		OutputStream out = null;

		try {
			in = new FileInputStream(source);
			out = new FileOutputStream(dest);
			byte[] buf = new byte[1024];
			int len;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			logger.info("Arquivo [" + dest.getName() + "] copiado com sucesso.");
		} finally {
			in.close();
			out.close();
		}
	}

	/**
	 * Altera as permissoes do diretorio do arquivo
	 * 
	 * @param filedir
	 * @throws IOException
	 */
	public void changePermissions(File filedir) throws IOException {
		for (File f : FileUtils.listFilesAndDirs(filedir, TrueFileFilter.TRUE,
				TrueFileFilter.TRUE)) {
			if (!f.setReadable(true, false)) {
				throw new IOException(String.format(
						" Failed to setReadable for all on %s", f));
			}
			if (!f.setWritable(true, false)) {
				throw new IOException(String.format(
						" Failed to setWritable for all on %s", f));
			}
			if (!f.setExecutable(true, false)) {
				throw new IOException(String.format(
						" Failed to setExecutable for all on %s", f));
			}
		}
	}

	/**
	 * remove os arquivos que ja foram processados
	 * 
	 * @param resource
	 * @throws IOException
	 */
	public void removeFiles(File resource) throws IOException {
		if (resource.isDirectory()) {
			File[] childFiles = resource.listFiles();
			for (File child : childFiles) {
				removeFiles(child);
			}
		}
		resource.delete();
		logger.info("Arquivo [" + resource.getName() + "] deletado com sucesso.");
	}
}