package com.nts.school.vo.person;

public class Teacher extends Person {
	private int subjectId;

	public Teacher() {

	}

	public Teacher(int id, String name, String birthDate, int subjectId) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.subjectId = subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	public int getSubjectId() {
		return subjectId;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s %d %d", this.id, this.name, this.birthDate, this.subjectId);
		return stringFormat;
	}
}
