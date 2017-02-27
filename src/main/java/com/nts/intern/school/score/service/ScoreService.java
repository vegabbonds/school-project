package com.nts.intern.school.score.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.school.person.dao.StudentDao;
import com.nts.intern.school.score.dao.ScoreDao;
import com.nts.intern.school.score.vo.Score;
import com.nts.intern.school.score.vo.StudentAvgScore;
import com.nts.intern.school.score.vo.StudentScore;
import com.nts.intern.school.subject.dao.SubjectDao;
import com.nts.intern.school.util.message.ResultMessage;

@Service
public class ScoreService {
	@Autowired
	ScoreDao scoreDao;

	@Autowired
	StudentDao studentDao;

	@Autowired
	SubjectDao subjectDao;

	public ResultMessage addScore(Score score) {
		if (studentDao.isStudentExistById(score.getStudentId()) == null) {
			return ResultMessage.ADD_FAIL;
		}

		if (subjectDao.isSubjectExistById(score.getSubjectId()) == null) {
			return ResultMessage.ADD_FAIL;
		}

		try {
			scoreDao.addScore(score);
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			return ResultMessage.ADD_FAIL;
		}
	}

	public ResultMessage deleteScore(Score score) {
		if (scoreDao.deleteScore(score) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.DELETE_FAIL;
	}

	public ResultMessage modifyScore(Score score) {
		if (scoreDao.modifyScore(score) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.MODIFY_FAIL;
	}

	public List<StudentScore> searchScores() {
		return scoreDao.searchScores();
	}

	public List<StudentScore> searchSpecificScores(int searchValue) {
		return scoreDao.searchScoresByStudentId(searchValue);
	}

	public List<StudentAvgScore> searchAvgScores() {
		return scoreDao.searchAvgScores();
	}

	public List<StudentAvgScore> searchAvgScoresByStudentId(int studentId) {
		return scoreDao.searchAvgScoresByStudentId(studentId);
	}

	public double searchOverallAvgScore() {
		Double overallAvg = scoreDao.searchOverallAvgScore();
		if (overallAvg == null) {
			return 0;
		}
		return overallAvg;
	}
}
