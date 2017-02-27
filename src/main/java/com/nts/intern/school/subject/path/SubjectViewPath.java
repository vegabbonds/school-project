package com.nts.intern.school.subject.path;

public enum SubjectViewPath {
	SUBJECT_PAGE("view/subject/searchSubjectPage");

	private String viewPath;

	private SubjectViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getPath() {
		return this.viewPath;
	}
}
