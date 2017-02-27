package com.nts.school.util.path;

public enum ViewPath {
	STAFF_PAGE("/WEB-INF/jsp/view/staff/searchStaffPage.jsp"),
	STUDENT_PAGE("/WEB-INF/jsp/view/student/searchStudentPage.jsp"),
	TEACHER_PAGE("/WEB-INF/jsp/view/teacher/searchTeacherPage.jsp"),
	SUBJECT_PAGE("/WEB-INF/jsp/view/subject/searchSubjectPage.jsp"),
	SCORE_PAGE("/WEB-INF/jsp/view/score/searchScorePage.jsp"),
	AVG_SCORE_PAGE("/WEB-INF/jsp/view/score/searchAvgScorePage.jsp"),
	OVERALL_AVG_SCORE_PAGE("/WEB-INF/jsp/view/score/searchOverallAvgScorePage.jsp"),
	ERROR_PAGE("/errorPage.jsp");

	private String viewPath;

	private ViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public String getPath() {
		return this.viewPath;
	}
}
