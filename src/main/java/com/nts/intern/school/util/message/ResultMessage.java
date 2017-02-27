package com.nts.intern.school.util.message;

public enum ResultMessage {
	ADD_FAIL("Add process is failed"),
	DELETE_FAIL("Delete process is failed"),
	MODIFY_FAIL("Modify process is failed"),
	INPUT_FORMAT_ERROR("You entered an invalid value."),
	SUCCESS("Success!");

	private String message;

	private ResultMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
