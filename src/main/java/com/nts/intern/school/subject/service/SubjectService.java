package com.nts.intern.school.subject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.intern.school.person.dao.TeacherDao;
import com.nts.intern.school.score.dao.ScoreDao;
import com.nts.intern.school.subject.constant.SubjectConstant;
import com.nts.intern.school.subject.dao.SubjectDao;
import com.nts.intern.school.subject.vo.Subject;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.option.SearchOption;

@Service
public class SubjectService {
	@Autowired
	SubjectDao subjectDao;

	@Autowired
	ScoreDao scoreDao;

	@Autowired
	TeacherDao teacherDao;

	public ResultMessage addSubject(Subject subject) {
		if (subjectDao.getSubjectCount() >= SubjectConstant.MAX_SUBJECT) {
			return ResultMessage.ADD_FAIL;
		}

		try {
			subjectDao.addSubject(subject);
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			return ResultMessage.ADD_FAIL;
		}
	}

	@Transactional
	public ResultMessage deleteSubject(int id) {
		if (subjectDao.isSubjectExistById(id) == null) {
			return ResultMessage.DELETE_FAIL;
		}

		scoreDao.deleteScoreBySubject(id);
		teacherDao.deleteTeacherBySubject(id);
		subjectDao.deleteSubject(id);
		return ResultMessage.SUCCESS;
	}

	public ResultMessage modifySubject(Subject subject) {
		if (subjectDao.modifySubject(subject) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.MODIFY_FAIL;
	}

	public List<Subject> searchSubjects() {
		return subjectDao.searchSubjects();
	}

	public List<Subject> searchSpecificSubjects(String selectOption, String searchValue) {
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchSubjectsById(searchValue);
			case NAME:
				return searchSubjectsByName(searchValue);
			default:
				return new ArrayList<Subject>();
		}
	}

	public List<Subject> searchSubjectsById(String searchValue) {
		return subjectDao.searchSubjectsById(Integer.parseInt(searchValue));
	}

	public List<Subject> searchSubjectsByName(String searchValue) {
		return subjectDao.searchSubjectsByName(searchValue);
	}
}
