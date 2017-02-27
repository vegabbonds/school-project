package com.nts.school.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import com.nts.school.util.Constant;
import com.nts.school.vo.object.Score;
import com.nts.school.vo.object.Subject;
import com.nts.school.vo.person.Person;
import com.nts.school.vo.person.Staff;
import com.nts.school.vo.person.Student;
import com.nts.school.vo.person.Teacher;

public class ValidateService {

	public boolean checkPersonValidation(Person person) {
		if (person instanceof Student) {
			return checkStudentValidation(person);

		} else if (person instanceof Staff) {
			return checkStaffValidation(person);

		} else if (person instanceof Teacher) {
			return checkTeacherValidation(person);

		} else {
			return false;
		}
	}

	private boolean checkStudentValidation(Person student) {
		int id = student.getId();
		String name = student.getName();
		String birthDate = student.getBirthDate();

		boolean validation = checkStudentIdValidation(id) && checkNameValidation(name)
			&& checkBirthDateValidation(birthDate);

		return validation;
	}

	private boolean checkStaffValidation(Person staff) {
		int id = staff.getId();
		String name = staff.getName();
		String birthDate = staff.getBirthDate();

		boolean validation = checkStaffIdValidation(id) && checkNameValidation(name)
			&& checkBirthDateValidation(birthDate);

		return validation;
	}

	private boolean checkTeacherValidation(Person checkTeacher) {
		Teacher teacher = (Teacher)checkTeacher;
		int id = teacher.getId();
		String name = teacher.getName();
		String birthDate = teacher.getBirthDate();
		int subjectId = teacher.getSubjectId();
		boolean validation = checkTeacherIdValidation(id) && checkNameValidation(name)
			&& checkBirthDateValidation(birthDate) && checkSubjectIdValidation(subjectId);

		return validation;
	}

	public boolean checkScoreValidation(Score score) {
		int studentId = score.getStudentId();
		int subjectId = score.getSubjectId();
		int subjectScore = score.getSubjectScore();
		boolean validation = checkStudentIdValidation(studentId) && checkSubjectIdValidation(subjectId)
			&& checkSubjectScoreValidation(subjectScore);
		return validation;
	}

	public boolean checkSubjectValidation(Subject subject) {
		int subjectId = subject.getSubjectId();
		String subjectName = subject.getSubjectName();
		boolean validation = checkSubjectIdValidation(subjectId) && checkNameValidation(subjectName);
		return validation;
	}

	public boolean checkStudentIdValidation(int studentId) {
		if (studentId >= Constant.MIN_STUDENT_ID && studentId <= Constant.MAX_STUDENT_ID) {
			return true;
		}
		return false;
	}

	public boolean checkStaffIdValidation(int staffId) {
		if (staffId >= Constant.MIN_STAFF_ID && staffId <= Constant.MAX_STAFF_ID) {
			return true;
		}
		return false;
	}

	public boolean checkTeacherIdValidation(int teacherId) {
		if (teacherId >= Constant.MIN_TEACHER_ID && teacherId <= Constant.MAX_TEACHER_ID) {
			return true;
		}
		return false;
	}

	public boolean checkSubjectIdValidation(int subjectId) {
		if (subjectId >= Constant.MIN_SUBJECT_ID && subjectId <= Constant.MAX_SUBJECT_ID) {
			return true;
		}
		return false;
	}

	public boolean checkBirthDateValidation(String birthDate) {
		boolean dateValidity = true;

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);

		try {
			Date date = dateFormat.parse(birthDate);
			System.out.println(date);
		} catch (ParseException pe) {
			dateValidity = false;
		} catch (IllegalArgumentException ae) {
			dateValidity = false;
		}
		return dateValidity;
	}

	public boolean checkNameValidation(String name) {
		if (name.length() > Constant.MAX_NAME_LENGTH || name.length() < Constant.MIN_NAME_LENGTH) {
			return false;
		}
		boolean namePattern = Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", name);
		return namePattern;
	}

	public boolean checkSubjectScoreValidation(int subjectScore) {
		if (subjectScore >= Constant.MIN_SCORE && subjectScore <= Constant.MAX_SCORE) {
			return true;
		}
		return false;
	}

}
