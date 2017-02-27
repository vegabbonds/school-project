package com.nts.school.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nts.school.dao.ScoreDao;
import com.nts.school.dao.StudentDao;
import com.nts.school.dao.SubjectDao;
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.object.Score;
import com.nts.school.vo.viewobject.StudentAvg;
import com.nts.school.vo.viewobject.StudentScore;

@RunWith(MockitoJUnitRunner.class)
public class ScoreServiceTest {

	@Mock
	public ScoreDao scoreDao;

	@Mock
	public StudentDao studentDao;

	@Mock
	public SubjectDao subjectDao;

	@InjectMocks
	public ScoreService scoreService;

	@Test
	public void testAddScoreSuccess() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = scoreService.addScore(score);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testAddScoreFail_원인_studentId부재() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(false);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.ADD_FAIL);

		ResultMessage message = scoreService.addScore(score);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testAddScoreFail_원인_subjectId부재() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.ADD_FAIL);

		ResultMessage message = scoreService.addScore(score);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testModifyScoreSuccess() {
		//given
		Score score1 = new Score(12345678, 12345, 70);

		//when				
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);
		when(scoreDao.modifyScore((Score)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = scoreService.modifyScore(score1);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testModifyScoreFail_원인_studentId부재() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(false);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.MODIFY_FAIL);

		ResultMessage message = scoreService.modifyScore(score);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testModifyScoreFail_원인_subjectId부재() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.MODIFY_FAIL);

		ResultMessage message = scoreService.modifyScore(score);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testDeleteScoreSuccess() {
		//given
		Score score1 = new Score(12345678, 12345, 70);

		//when				
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);
		when(scoreDao.deleteScore((Score)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = scoreService.deleteScore(score1);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testDeleteScoreFail_원인_studentId부재() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(false);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.DELETE_FAIL);

		ResultMessage message = scoreService.deleteScore(score);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testDeleteScoreFail_원인_subjectId부재() {
		//given
		Score score = new Score(12345678, 12345, 70);

		//when		
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);
		when(scoreDao.addScore((Score)anyObject())).thenReturn(ResultMessage.DELETE_FAIL);

		ResultMessage message = scoreService.deleteScore(score);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testSearchScore() {
		//given
		List<StudentScore> scores = new ArrayList<StudentScore>();
		StudentScore score1 = new StudentScore(12345678, "jeong", 12345, "math", 60);
		StudentScore score2 = new StudentScore(12345678, "jeong", 12346, "kor", 70);
		StudentScore score3 = new StudentScore(12345679, "good", 12346, "math", 80);
		StudentScore score4 = new StudentScore(12345679, "good", 12346, "kor", 90);
		scores.add(score1);
		scores.add(score2);
		scores.add(score3);
		scores.add(score4);

		//when		
		when(scoreDao.searchScore()).thenReturn(scores);

		List<StudentScore> searchScores = new ArrayList<StudentScore>();
		searchScores = scoreService.searchScore();

		//then
		assertThat(searchScores.size(), is(4));
		assertThat(searchScores.contains(score1), is(true));
		assertThat(searchScores.contains(score2), is(true));
	}

	@Test
	public void testSearchSpecificScore() throws Exception {
		//given
		List<StudentScore> scores = new ArrayList<StudentScore>();
		StudentScore score1 = new StudentScore(12345678, "jeong", 12345, "math", 60);
		StudentScore score2 = new StudentScore(12345678, "jeong", 12346, "kor", 70);
		StudentScore score3 = new StudentScore(12345679, "good", 12346, "math", 80);
		StudentScore score4 = new StudentScore(12345679, "good", 12346, "kor", 90);
		scores.add(score1);
		scores.add(score2);
		scores.add(score3);
		scores.add(score4);
		List<StudentScore> searchScoreById = new ArrayList<StudentScore>();
		searchScoreById.add(score1);
		searchScoreById.add(score2);

		//when		
		when(scoreDao.searchScoreByStudentId(anyInt())).thenReturn(searchScoreById);

		List<StudentScore> searchScores = new ArrayList<StudentScore>();
		searchScores = scoreService.searchSpecificScore(12345678);

		//then
		assertThat(searchScores.size(), is(2));
		assertThat(searchScores.contains(score1), is(true));
		assertThat(searchScores.contains(score2), is(true));
	}

	@Test
	public void testSearchAvgScore() throws Exception {
		//given
		List<StudentAvg> scores = new ArrayList<StudentAvg>();
		StudentAvg score1 = new StudentAvg(12345678, "jeong", 60);
		StudentAvg score2 = new StudentAvg(12345679, "mike", 70);
		StudentAvg score3 = new StudentAvg(12345680, "tom", 80);
		StudentAvg score4 = new StudentAvg(12345681, "jane", 90);
		scores.add(score1);
		scores.add(score2);
		scores.add(score3);
		scores.add(score4);

		//when
		when(scoreDao.searchAvgScoreByStudent()).thenReturn(scores);
		List<StudentAvg> searchAvgScores = new ArrayList<StudentAvg>();
		searchAvgScores = scoreService.searchAvgScore();

		//then
		assertThat(searchAvgScores.size(), is(4));
		assertThat(searchAvgScores.contains(score1), is(true));
		assertThat(searchAvgScores.contains(score2), is(true));
		assertThat(searchAvgScores.contains(score3), is(true));
		assertThat(searchAvgScores.contains(score4), is(true));
	}

	@Test
	public void testSearchSpecificStudentAvgScore() throws Exception {
		//given
		List<StudentAvg> scores = new ArrayList<StudentAvg>();
		StudentAvg score1 = new StudentAvg(12345678, "jeong", 60);
		StudentAvg score2 = new StudentAvg(12345679, "mike", 70);
		StudentAvg score3 = new StudentAvg(12345680, "tom", 80);
		StudentAvg score4 = new StudentAvg(12345681, "jane", 90);
		scores.add(score1);
		scores.add(score2);
		scores.add(score3);
		scores.add(score4);
		List<StudentAvg> searchAvg = new ArrayList<StudentAvg>();
		searchAvg.add(score1);

		//when
		when(scoreDao.searchAvgScoreBySpecificStudent(anyInt())).thenReturn(searchAvg);
		List<StudentAvg> searchAvgBySpecificStudent = new ArrayList<StudentAvg>();
		searchAvgBySpecificStudent = scoreService.searchAvgBySpecificStudent(12345678);

		//then
		assertThat(searchAvgBySpecificStudent.size(), is(1));
		assertThat(searchAvgBySpecificStudent.contains(score1), is(true));
	}

	@Test
	public void testSearchOverallAvgScore() throws Exception {
		//given
		double avgScore = (60 + 70 + 80 + 90) / 4;

		//when
		when(scoreDao.searchOverallAvgScore()).thenReturn(avgScore);
		double overallAvgScore = scoreService.searchOvearallAvgScore();

		//then
		assertThat(overallAvgScore, is(avgScore));
	}
}
