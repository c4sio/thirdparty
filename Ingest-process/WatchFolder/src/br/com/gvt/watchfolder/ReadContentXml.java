package br.com.gvt.watchfolder;

import java.util.ArrayList;

import br.com.gvt.watchfolder.json.JSONArray;

public interface ReadContentXml {

	/**
	 * @param xmlValues
	 * @return JSONArray
	 */
	public abstract JSONArray readContent(String xmlValues);

	/**
	 * @param xmlValues
	 * @return String
	 */
	public abstract String getAssetInfoByContent(String xmlValues);

	/**
	 * @param xmlValues
	 * @return String
	 */
	public abstract String getAssetTileByContent(String xmlValues);

	/**
	 * @param pathfile
	 * @param objectValues
	 * @return boolean
	 */
	public abstract boolean validateMD5(String pathfile, JSONArray objectValues);

	/**
	 * @param objectValues
	 * @return boolean
	 */
	public abstract ArrayList<String> findNameFiles(JSONArray objectValues);

}
