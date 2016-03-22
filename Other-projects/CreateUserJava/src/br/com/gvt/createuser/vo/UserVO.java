package br.com.gvt.createuser.vo;

import java.io.Serializable;

public class UserVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String email;
	private String subscreberId;
	private String password;
	private String androidId;
	private String appleId;
	private String retorno;
	private boolean error;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubscreberId() {
		return subscreberId;
	}

	public void setSubscreberId(String subscreberId) {
		this.subscreberId = subscreberId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAndroidId() {
		return androidId;
	}

	public void setAndroidId(String androidId) {
		this.androidId = androidId;
	}

	public String getAppleId() {
		return appleId;
	}

	public void setAppleId(String appleId) {
		this.appleId = appleId;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

}
