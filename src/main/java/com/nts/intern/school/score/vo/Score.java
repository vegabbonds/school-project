package com.nts.intern.school.score.vo;

public class Score {
	private int studentId;
	private int subjectId;
	private int grade;

	public Score() {
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %d %d", studentId, subjectId, grade);
		return stringFormat;
	}
}
