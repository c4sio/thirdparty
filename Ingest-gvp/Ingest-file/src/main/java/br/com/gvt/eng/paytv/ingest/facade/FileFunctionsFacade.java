package br.com.gvt.eng.paytv.ingest.facade;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileFunctionsFacade {

	/**
	 * @param path
	 * @return List<File>
	 */
	public abstract List<File> searchFiles(String path);

	/**
	 * @param pathFrom
	 * @param pathTo
	 * @return boolean
	 * @throws IOException
	 */
	public abstract boolean copyFiles(Path pathFrom, Path pathTo)
			throws IOException;

	/**
	 * @param listFiles
	 * @throws IOException
	 */
	public abstract void remameFileToDone(List<File> listFiles)
			throws IOException;

	/**
	 * @param pathFile
	 * @return boolean
	 * @throws IOException
	 */
	public abstract boolean createFileReadOk(String pathFile)
			throws IOException;

	/**
	 * @param path
	 * @return boolean
	 */
	public abstract boolean searchFileGvp(String path);

}
