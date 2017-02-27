package com.nts.school.service;

import java.util.ArrayList;
import java.util.List;

import com.nts.school.dao.ScoreDao;
import com.nts.school.dao.StudentDao;
import com.nts.school.util.Constant;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.SearchOption;
import com.nts.school.vo.person.Student;

public class StudentService {

	private StudentDao studentDao;
	private ScoreDao scoreDao;

	public StudentService() {
		studentDao = new StudentDao();
		scoreDao = new ScoreDao();
	}

	public ResultMessage addStudent(Student student) {
		if (studentDao.getStudentCount() >= Constant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		if (studentDao.isStudentExistById(student.getId()) == true) {
			return ResultMessage.ADD_FAIL;
		}

		return studentDao.addStudent(student);
	}

	public ResultMessage modifyStudent(Student student) {
		if (studentDao.isStudentExistById(student.getId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		return studentDao.modifyStudent(student);
	}

	public ResultMessage deleteStudent(int studentId) {
		ResultMessage message = ResultMessage.SUCCESS;

		if (scoreDao.isScoreExistByStudentId(studentId)) {
			message = scoreDao.deleteScoreByStudent(studentId);
		}

		if (message != ResultMessage.SUCCESS) {
			return ResultMessage.DELETE_FAIL;
		}

		if (studentDao.isStudentExistById(studentId) == false) {
			return ResultMessage.DELETE_FAIL;
		}

		return studentDao.deleteStudent(studentId);
	}

	public List<Student> searchStudent() {
		return studentDao.searchStudent();
	}

	public List<Student> searchSpecificStudent(String getData, String selectOption) throws Exception {
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchStudentById(getData);
			case NAME:
				return searchStudentByName(getData);
			default:
				return new ArrayList<Student>();
		}
	}

	public List<Student> searchStudentById(String id) throws NumberFormatException {
		return studentDao.searchStudentById(Integer.parseInt(id));
	}

	public List<Student> searchStudentByName(String name) {
		return studentDao.searchStudentByName(name);
	}

}
