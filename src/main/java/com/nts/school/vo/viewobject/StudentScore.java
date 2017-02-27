package com.nts.school.vo.viewobject;

public class StudentScore {
	private int id;
	private String name;
	private int subjectId;
	private String subjectName;
	private int score;

	public StudentScore() {

	}

	public StudentScore(int id, String name, int subjectId, String subjectName, int score) {
		this.id = id;
		this.name = name;
		this.subjectId = subjectId;
		this.subjectName = subjectName;
		this.score = score;
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}
}
