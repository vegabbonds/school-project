package com.nts.intern.school.person.vo;

public class Person {
	protected int id;
	protected String name;
	protected String birth;

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

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s %d", this.id, this.name, this.birth);
		return stringFormat;
	}
}
