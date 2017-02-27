package com.nts.intern.school.subject.vo;

public class Subject {
	private int id;
	private String name;

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

	@Override
	public String toString() {
		String stringFormat = String.format("%d %s", this.id, this.name);
		return stringFormat;
	}
}
