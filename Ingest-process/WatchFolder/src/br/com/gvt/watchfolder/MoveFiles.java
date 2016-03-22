package br.com.gvt.watchfolder;

public interface MoveFiles {

	/**
	 * Move os arquivos para o diretorio de entrada do 4Balancer
	 * 
	 * @param pathfile
	 * @param assetInfo
	 * 
	 * @return boolean
	 */
	public abstract boolean moveFilesToExecute(String assetInfo, String pathfile);

}