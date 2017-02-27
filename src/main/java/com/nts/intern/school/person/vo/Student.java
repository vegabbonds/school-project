package com.nts.intern.school.person.vo;

public class Student extends Person {

	public Student() {

	}

	public Student(int id, String name, String birth) {
		this.id = id;
		this.name = name;
		this.birth = birth;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s %d", this.id, this.name, this.birth);
		return stringFormat;
	}
}
