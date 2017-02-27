package com.nts.intern.school.person.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.school.person.constant.PersonConstant;
import com.nts.intern.school.person.dao.StudentDao;
import com.nts.intern.school.person.vo.Student;
import com.nts.intern.school.score.dao.ScoreDao;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.option.SearchOption;

@Service
public class StudentService {
	@Autowired
	StudentDao studentDao;

	@Autowired
	ScoreDao scoreDao;

	public ResultMessage addStudent(Student student) {
		if (studentDao.getStudentCount() >= PersonConstant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		try {
			studentDao.addStudent(student);
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			return ResultMessage.ADD_FAIL;
		}
	}

	@Transactional
	public ResultMessage deleteStudent(int id) {
		if (studentDao.isStudentExistById(id) == null) {
			return ResultMessage.DELETE_FAIL;
		}

		scoreDao.deleteScoreByStudent(id);
		studentDao.deleteStudent(id);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage modifyStudent(Student student) {
		if (studentDao.modifyStudent(student) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.MODIFY_FAIL;
	}

	public List<Student> searchStudents() {
		return studentDao.searchStudents();
	}

	public List<Student> searchSpecificStudents(String selectOption, String searchValue) {
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchStudentsById(searchValue);
			case NAME:
				return searchStudentsByName(searchValue);
			default:
				return new ArrayList<Student>();
		}
	}

	public List<Student> searchStudentsById(String searchValue) {
		return studentDao.searchStudentsById(Integer.parseInt(searchValue));
	}

	public List<Student> searchStudentsByName(String searchValue) {
		return studentDao.searchStudentsByName(searchValue);
	}
}
