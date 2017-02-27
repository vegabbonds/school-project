package com.nts.school.vo.person;

public class Person {
	protected int id;
	protected String name;
	protected String birthDate;

	public Person() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s %d", this.id, this.name, this.birthDate);
		return stringFormat;
	}
}
