package com.gvt.eng.ipvod.proccess.util;

import org.apache.log4j.Logger;

public enum MediaTypeEnum {

	poster("Poster"), preview("Trailer"), movie("Movie"), icon("Icon"), subtitle(
			"Subtitle");

	Logger logger = Logger.getLogger(MediaTypeEnum.class);

	MediaTypeEnum(String descricao) {
		this.descricao = descricao;
	}

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
