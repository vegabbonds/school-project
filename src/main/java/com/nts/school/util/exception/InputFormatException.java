package com.nts.school.util.exception;

public class InputFormatException extends Exception {
	private static final long serialVersionUID = 1L;
	private final int ERR_CODE;

	InputFormatException(String msg, int errCode) {
		super(msg);
		ERR_CODE = errCode;
	}

	public InputFormatException(String msg) {
		this(msg, 100);
	}

	public int getErrCode() {
		return ERR_CODE;
	}

}
