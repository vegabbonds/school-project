package com.nts.school.util;

public enum ResultMessage {
	ADD_FAIL("Add process is failed"),
	DELETE_FAIL("Delete process is failed"),
	MODIFY_FAIL("Modify process is failed"),
	SUCCESS("Success!");

	private String message;

	private ResultMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
