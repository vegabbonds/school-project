package com.nts.intern.school.person.path;

public enum PersonViewPath {	
	STAFF_PAGE("view/staff/searchStaffPage"),
	STUDENT_PAGE("view/student/searchStudentPage"),
	TEACHER_PAGE("view/teacher/searchTeacherPage"),	
	ERROR_PAGE("/errorPage.jsp");

	private String viewPath;

	private PersonViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getPath() {
		return this.viewPath;
	}	
}
