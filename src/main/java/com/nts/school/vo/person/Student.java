package com.nts.school.vo.person;

public class Student extends Person {

	public Student() {

	}

	public Student(int id, String name, String birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s %d", this.id, this.name, this.birthDate);
		return stringFormat;
	}
}
