package com.jsst.cloud.common.http;

public class OkHttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8429486798068406034L;
	
	private String respCode;
	private String respMessage;
	public OkHttpException(String respCode, String respMessage) {
		super();
		this.respCode = respCode;
		this.respMessage = respMessage;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMessage() {
		return respMessage;
	}
	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}
	
}
