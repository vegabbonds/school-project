package com.nts.school.vo.object;

public class Score {
	private int studentId;
	private int subjectId;
	private int subjectScore;

	public Score() {

	}

	public Score(int studentId, int subjectId, int subjectScore) {
		this.studentId = studentId;
		this.subjectId = subjectId;
		this.subjectScore = subjectScore;
	}

	public int getSubjectScore() {
		return subjectScore;
	}

	public void setSubjectScore(int subjectScore) {
		this.subjectScore = subjectScore;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %d %d", studentId, subjectId, subjectScore);
		return stringFormat;
	}
}
