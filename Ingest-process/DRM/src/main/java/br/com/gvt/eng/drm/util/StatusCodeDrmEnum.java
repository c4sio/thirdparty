package br.com.gvt.eng.drm.util;

public enum StatusCodeDrmEnum {

	Submitted(0), Pending(1), Processing(2), Aborted(3), Completed(4), Deleted(
			5), Waiting(6);

	private final int codigo;

	/**
	 * @param codigo
	 */
	StatusCodeDrmEnum(int codigo) {
		this.codigo = codigo;
	}

	int codigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 * @return StatusCodeDrmEnum
	 */
	public static StatusCodeDrmEnum porCodigo(int codigo) {
		for (StatusCodeDrmEnum statusCode : StatusCodeDrmEnum.values()) {
			if (codigo == statusCode.codigo())
				return statusCode;
		}
		throw new IllegalArgumentException("codigo invalido");
	}
}
