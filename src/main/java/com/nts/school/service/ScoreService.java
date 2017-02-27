package com.nts.school.service;

import java.util.List;

import com.nts.school.dao.ScoreDao;
import com.nts.school.dao.StudentDao;
import com.nts.school.dao.SubjectDao;
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.object.Score;
import com.nts.school.vo.viewobject.StudentAvg;
import com.nts.school.vo.viewobject.StudentScore;

/**
 * Servlet에서 요청한 작업을 Service에서 실행을 한다.
 * @author 이정석
 */
public class ScoreService {

	private ScoreDao scoreDao;
	private StudentDao studentDao;
	private SubjectDao subjectDao;

	public ScoreService() {
		scoreDao = new ScoreDao();
		studentDao = new StudentDao();
		subjectDao = new SubjectDao();
	}

	public ResultMessage addScore(Score score) {
		if (studentDao.isStudentExistById(score.getStudentId()) == false) {
			return ResultMessage.ADD_FAIL;
		}

		if (subjectDao.isSubjectExistById(score.getSubjectId()) == false) {
			return ResultMessage.ADD_FAIL;
		}

		return scoreDao.addScore(score);
	}

	/**
	 * 특정 과목을 modify를 수행.
	 * @param score
	 * @return
	 */
	public ResultMessage modifyScore(Score score) {
		if (studentDao.isStudentExistById(score.getStudentId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		if (subjectDao.isSubjectExistById(score.getSubjectId()) == false) {
			return ResultMessage.MODIFY_FAIL;
		}

		return scoreDao.modifyScore(score);
	}

	/**
	 * 특정 과목을 delete를 수행.
	 * @param score
	 * @return
	 */
	public ResultMessage deleteScore(Score score) {
		if (studentDao.isStudentExistById(score.getStudentId()) == false) {
			return ResultMessage.DELETE_FAIL;
		}

		if (subjectDao.isSubjectExistById(score.getSubjectId()) == false) {
			return ResultMessage.DELETE_FAIL;
		}

		return scoreDao.deleteScore(score);
	}

	public List<StudentScore> searchScore() {
		return scoreDao.searchScore();
	}

	/**
	 * 특정 조건을 만족하는 성적을 조회.
	 * @param specificStudentId
	 * @return
	 */
	public List<StudentScore> searchSpecificScore(int specificStudentId) {
		return scoreDao.searchScoreByStudentId(specificStudentId);
	}

	/**
	 * 학생들의 각 평균을 저장한 ArrayList를 return.
	 * @return
	 */
	public List<StudentAvg> searchAvgScore() {
		return scoreDao.searchAvgScoreByStudent();
	}

	public List<StudentAvg> searchAvgBySpecificStudent(int specificStudentId) {
		return scoreDao.searchAvgScoreBySpecificStudent(specificStudentId);
	}

	/**
	 * 학생들의 전체평균을 return.
	 * 각 학생들의 평균을 더하고 인원으로 나누었다.
	 * @return
	 */
	public double searchOvearallAvgScore() {
		return scoreDao.searchOverallAvgScore();
	}
}
