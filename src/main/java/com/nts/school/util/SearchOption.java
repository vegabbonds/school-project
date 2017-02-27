package com.nts.school.util;

public enum SearchOption {
	ID("id"),
	NAME("name"),
	BIRTH_DATE("birthDate"),
	SUBJECT_ID("subjectId"),
	NAN("NaN");

	private String option;

	private SearchOption(String option) {
		this.option = option;
	}

	private String getOption() {
		return option;
	}

	public static SearchOption findSearchOption(String option) {

		for (SearchOption searchOption : SearchOption.values()) {
			if (option.equals(searchOption.getOption())) {
				return searchOption;
			}
		}

		return NAN;
	}
}
