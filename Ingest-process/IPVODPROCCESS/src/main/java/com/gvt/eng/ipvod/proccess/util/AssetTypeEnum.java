package com.gvt.eng.ipvod.proccess.util;

import org.apache.log4j.Logger;

public enum AssetTypeEnum {

	movie("Filmes"), documentary("Documentário"), show("Shows");

	Logger logger = Logger.getLogger(AssetTypeEnum.class);

	AssetTypeEnum(String descricao) {
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
