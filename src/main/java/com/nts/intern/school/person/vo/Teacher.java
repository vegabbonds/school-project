package com.nts.intern.school.person.vo;

public class Teacher extends Person {
	private int subjectId;

	public Teacher() {

	}

	public Teacher(int id, String name, String birth, int subjectId) {
		this.id = id;
		this.name = name;
		this.birth = birth;
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
		String stringFormat = String.format("%d %s %d %d", this.id, this.name, this.birth, this.subjectId);
		return stringFormat;
	}
}
