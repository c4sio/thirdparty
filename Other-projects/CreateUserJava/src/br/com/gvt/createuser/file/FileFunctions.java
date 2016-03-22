package br.com.gvt.createuser.file;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileFunctions {

	/**
	 * @param value
	 */
	public void writeInFile(String value) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("createUser.txt", true));
			bw.write(value);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
				}
			}
		}
	}

}
