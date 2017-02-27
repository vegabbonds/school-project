package com.nts.intern.school.score.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.intern.school.score.vo.Score;
import com.nts.intern.school.score.vo.StudentAvgScore;
import com.nts.intern.school.score.vo.StudentScore;

@Mapper
public interface ScoreDao {
	public Integer addScore(Score score) throws Exception;;

	public Integer deleteScore(Score score);

	public Integer deleteScoreByStudent(int studentId);

	public Integer deleteScoreBySubject(int subjectId);

	public Integer modifyScore(Score score);

	public List<StudentScore> searchScores();

	public List<StudentScore> searchScoresByStudentId(int studentId);
	
	public List<StudentAvgScore> searchAvgScores();
	
	public List<StudentAvgScore> searchAvgScoresByStudentId(int studentId);

	public Double searchOverallAvgScore();
	
	public List<Integer> isScoreExistByStudentId(int studentId);

	public List<Integer> isScoreExistBySubjectId(int subjectId);
}
