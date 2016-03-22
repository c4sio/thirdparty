package br.com.gvt.watchfolder.impl;

import java.io.File;

import org.apache.log4j.Logger;

import br.com.gvt.watchfolder.MoveFiles;
import br.com.gvt.watchfolder.shell.ExecUtils;
import br.com.gvt.watchfolder.util.Util;

public class MoveFilesImpl implements MoveFiles {

	static Logger logger = Logger.getLogger(MoveFilesImpl.class.getName());

	@Override
	public boolean moveFilesToExecute(String assetInfo, String pathfile) {
		boolean fileIsMoved = false;
		try {
			logger.info("Movendo os arquivos para o diretorio de entrada 4Balancer - "
					+ assetInfo);
			ExecUtils exec = new ExecUtils();
			String command_mk = "mkdir " + Util.getPathFile() + assetInfo;
			String command = "cp -f "
					+ pathfile
					+ "*.{jpg,JPG,png,PNG,xml,XML,dtd,DTD,ts,TS,mp4,MP4,mov,Mov,mxf,MXF,ps,PS,srt,SRT,mpg,MPG,mpeg,MPEG} "
					+ Util.getPathFile() + assetInfo + File.separator;
			String command_chmod = "chmod -R 777 " + Util.getPathFile() + assetInfo;

			logger.info("mkdir: " + exec.execShellCmd(command_mk));
			logger.info("cp: " + exec.execShellCmd(command));
			logger.info("chmod: " + exec.execShellCmd(command_chmod));
			fileIsMoved = true;
		} catch (Exception e) {
			logger.error("Erro ao mover os arquivos para o diretorio de entrada 4Balancer - "
					+ assetInfo + ", " + e);
		}
		return fileIsMoved;
	}
}
