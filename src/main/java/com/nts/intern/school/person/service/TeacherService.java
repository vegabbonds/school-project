package com.nts.intern.school.person.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.school.person.constant.PersonConstant;
import com.nts.intern.school.person.dao.TeacherDao;
import com.nts.intern.school.person.vo.Teacher;
import com.nts.intern.school.subject.dao.SubjectDao;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.option.SearchOption;

@Service
public class TeacherService {
	@Autowired
	TeacherDao teacherDao;

	@Autowired
	SubjectDao subjectDao;

	public ResultMessage addTeacher(Teacher teacher) {
		if (teacherDao.getTeacherCount() >= PersonConstant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		if (subjectDao.isSubjectExistById(teacher.getSubjectId()) == null) {
			return ResultMessage.ADD_FAIL;
		}

		try {
			teacherDao.addTeacher(teacher);
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			return ResultMessage.ADD_FAIL;
		}
	}

	public ResultMessage deleteTeacher(int id) {
		if (teacherDao.deleteTeacher(id) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.DELETE_FAIL;
	}

	public ResultMessage modifyTeacher(Teacher teacher) {
		if (subjectDao.isSubjectExistById(teacher.getSubjectId()) == null) {
			return ResultMessage.MODIFY_FAIL;
		}

		if (teacherDao.modifyTeacher(teacher) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.MODIFY_FAIL;
	}

	public List<Teacher> searchTeachers() {
		return teacherDao.searchTeachers();
	}

	public List<Teacher> searchSpecificTeachers(String selectOption, String searchValue) {
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchTeachersById(searchValue);
			case NAME:
				return searchTeachersByName(searchValue);
			default:
				return new ArrayList<Teacher>();
		}
	}

	public List<Teacher> searchTeachersById(String searchValue) {
		return teacherDao.searchTeachersById(Integer.parseInt(searchValue));
	}

	public List<Teacher> searchTeachersByName(String searchValue) {
		return teacherDao.searchTeachersByName(searchValue);
	}
}
