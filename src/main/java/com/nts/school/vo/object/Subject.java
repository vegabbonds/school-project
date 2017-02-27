package com.nts.school.vo.object;

public class Subject {
	private String subjectName;
	private int subjectId;

	public Subject() {

	}

	public Subject(int subjectId, String subjectName) {
		this.subjectName = subjectName;
		this.subjectId = subjectId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s", this.subjectId, this.subjectName);
		return stringFormat;
	}
}
