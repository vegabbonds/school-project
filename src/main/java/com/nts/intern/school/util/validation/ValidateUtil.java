package com.nts.intern.school.util.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.nts.intern.school.person.constant.PersonConstant;
import com.nts.intern.school.person.vo.Person;
import com.nts.intern.school.person.vo.Staff;
import com.nts.intern.school.person.vo.Student;
import com.nts.intern.school.person.vo.Teacher;
import com.nts.intern.school.score.constant.ScoreConstant;
import com.nts.intern.school.score.vo.Score;
import com.nts.intern.school.subject.constant.SubjectConstant;
import com.nts.intern.school.subject.vo.Subject;
import com.nts.intern.school.util.constant.Constant;
import com.nts.intern.school.util.option.SearchOption;

@Service
public class ValidateUtil {
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
		String birthDate = student.getBirth();

		boolean validation = checkStudentIdValidation(id) && checkNameValidation(name)
				&& checkBirthValidation(birthDate);

		return validation;
	}

	private boolean checkStaffValidation(Person staff) {
		int id = staff.getId();
		String name = staff.getName();
		String birthDate = staff.getBirth();

		boolean validation = checkStaffIdValidation(id) && checkNameValidation(name) && checkBirthValidation(birthDate);

		return validation;
	}

	private boolean checkTeacherValidation(Person checkTeacher) {
		Teacher teacher = (Teacher) checkTeacher;
		int id = teacher.getId();
		String name = teacher.getName();
		String birthDate = teacher.getBirth();
		int subjectId = teacher.getSubjectId();
		boolean validation = checkTeacherIdValidation(id) && checkNameValidation(name)
				&& checkBirthValidation(birthDate) && checkSubjectIdValidation(subjectId);

		return validation;
	}

	public boolean checkScoreValidation(Score score) {
		int studentId = score.getStudentId();
		int subjectId = score.getSubjectId();
		int grade = score.getGrade();
		boolean validation = checkStudentIdValidation(studentId) && checkSubjectIdValidation(subjectId)
				&& checkGradeValidation(grade);
		return validation;
	}

	public boolean checkSubjectValidation(Subject subject) {
		int subjectId = subject.getId();
		String subjectName = subject.getName();
		boolean validation = checkSubjectIdValidation(subjectId) && checkNameValidation(subjectName);
		return validation;
	}

	public boolean checkStudentIdValidation(int studentId) {
		if (studentId >= PersonConstant.MIN_STUDENT_ID && studentId <= PersonConstant.MAX_STUDENT_ID) {
			return true;
		}
		return false;
	}

	public boolean checkStaffIdValidation(int staffId) {
		if (staffId >= PersonConstant.MIN_STAFF_ID && staffId <= PersonConstant.MAX_STAFF_ID) {
			return true;
		}
		return false;
	}

	public boolean checkTeacherIdValidation(int teacherId) {
		if (teacherId >= PersonConstant.MIN_TEACHER_ID && teacherId <= PersonConstant.MAX_TEACHER_ID) {
			return true;
		}
		return false;
	}

	public boolean checkSubjectIdValidation(int subjectId) {
		if (subjectId >= SubjectConstant.MIN_SUBJECT_ID && subjectId <= SubjectConstant.MAX_SUBJECT_ID) {
			return true;
		}
		return false;
	}

	public boolean checkBirthValidation(String birthDate) {
		boolean dateValidity = true;

		SimpleDateFormat dateFormat = new SimpleDateFormat(Constant.SIMPLE_DATE_FORMAT);
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
		if (name.length() > PersonConstant.MAX_NAME_LENGTH || name.length() < PersonConstant.MIN_NAME_LENGTH) {
			return false;
		}
		boolean namePattern = Pattern.matches(Constant.NAME_FORMAT, name);
		return namePattern;
	}

	public boolean checkGradeValidation(int grade) {
		if (grade >= ScoreConstant.MIN_SCORE && grade <= ScoreConstant.MAX_SCORE) {
			return true;
		}
		return false;
	}

	public boolean checkSearchSpecificInputValidation(String sel_opt, String search_value) {

		SearchOption selectOption = SearchOption.findSearchOption(sel_opt);
		switch (selectOption) {
			case ID:
				try {
					int searchValue = Integer.parseInt(search_value);
					if (searchValue > 0 && searchValue <= PersonConstant.MAX_STUDENT_ID) {
						return true;
					}
					return false;
				} catch (NumberFormatException e) {
					return false;
				}
			case NAME:
				return true;
			default:
				return false;
		}
	}

	public boolean checkSearchSpecificInputValidation(int searchValue) {
		try {
			if (searchValue > 0 && searchValue <= PersonConstant.MAX_STUDENT_ID) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
