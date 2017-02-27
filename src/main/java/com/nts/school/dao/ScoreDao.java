package com.nts.school.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nts.school.util.Constant;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.query.MySqlQuery;
import com.nts.school.vo.object.Score;
import com.nts.school.vo.viewobject.StudentAvg;
import com.nts.school.vo.viewobject.StudentScore;

/**
 * 서비스에서 데이터 베이스에 접근을 할 때
 * Dao를 통해서 접근을 하도록 하였다.
 * @author 이정석
 */
public class ScoreDao {

	private static Connection connection;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(Constant.CONNECTION_URL, Constant.ID, Constant.PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("MySqlJdbcDriver find Error" + e);
		} catch (SQLException e) {
			System.out.println("Connection Error : " + e);
		}
	}

	public ResultMessage addScore(Score score) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.ADD_SCORE);) {
			preparedStatement.setInt(1, score.getStudentId());
			preparedStatement.setInt(2, score.getSubjectId());
			preparedStatement.setInt(3, score.getSubjectScore());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("addScore Error : " + e);
			return ResultMessage.ADD_FAIL;
		}

	}

	public ResultMessage modifyScore(Score score) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.MODIFY_SCORE);) {
			preparedStatement.setInt(1, score.getSubjectScore());
			preparedStatement.setInt(2, score.getStudentId());
			preparedStatement.setInt(3, score.getSubjectId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("modifyScore Error : " + e);
			return ResultMessage.MODIFY_FAIL;
		}
	}

	public ResultMessage deleteScore(Score score) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_SCORE);) {
			preparedStatement.setInt(1, score.getStudentId());
			preparedStatement.setInt(2, score.getSubjectId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteScore Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public ResultMessage deleteScoreByStudent(int studentId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_SCORE_BY_STUDENT);) {
			preparedStatement.setInt(1, studentId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteScore Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public ResultMessage deleteScoreBySubject(int subjectId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_SCORE_BY_SUBJECT);) {
			preparedStatement.setInt(1, subjectId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteScore Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public List<StudentScore> searchScore() {
		List<StudentScore> studentScores = new ArrayList<StudentScore>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_SCORE);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int studentId = result.getInt("student_id");
					String studentName = result.getString("student_name");
					int subjectId = result.getInt("subject_id");
					String subjectName = result.getString("subject_name");
					int subjectScore = result.getInt("score");
					StudentScore studentScore = new StudentScore(studentId, studentName, subjectId, subjectName,
						subjectScore);
					studentScores.add(studentScore);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchScore Error : " + e);
		}

		return studentScores;
	}

	public List<StudentScore> searchScoreByStudentId(int specificStudentId) {
		List<StudentScore> studentScores = new ArrayList<StudentScore>();

		try (
			PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_SCORE_BY_STUDENT_ID);) {
			preparedStatement.setInt(1, specificStudentId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int studentId = result.getInt("student_id");
					String studentName = result.getString("student_name");
					int subjectId = result.getInt("subject_id");
					String subjectName = result.getString("subject_name");
					int subjectScore = result.getInt("score");
					StudentScore studentScore = new StudentScore(studentId, studentName, subjectId, subjectName,
						subjectScore);

					studentScores.add(studentScore);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchScore Error : " + e);
		}

		return studentScores;
	}

	public List<StudentAvg> searchAvgScoreByStudent() {
		List<StudentAvg> studentScores = new ArrayList<StudentAvg>();

		try (PreparedStatement preparedStatement = connection
			.prepareStatement(MySqlQuery.SEARCH_AVG_SCORE_BY_STUDENT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int studentId = result.getInt("student_id");
					String studentName = result.getString("student_name");
					double avgScore = result.getDouble("avg");
					StudentAvg studentAvg = new StudentAvg(studentId, studentName, avgScore);

					studentScores.add(studentAvg);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchScore Error : " + e);
		}

		return studentScores;
	}

	public List<StudentAvg> searchAvgScoreBySpecificStudent(int specificStudentId) {
		List<StudentAvg> studentScores = new ArrayList<StudentAvg>();

		try (PreparedStatement preparedStatement = connection
			.prepareStatement(MySqlQuery.SEARCH_AVG_SCORE_BY_SPECIFIC_STUDENT);) {
			preparedStatement.setInt(1, specificStudentId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int studentId = result.getInt("student_id");
					String studentName = result.getString("student_name");
					double avgScore = result.getDouble("avg");
					StudentAvg studentAvg = new StudentAvg(studentId, studentName, avgScore);

					studentScores.add(studentAvg);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchScore Error : " + e);
		}

		return studentScores;
	}

	public double searchOverallAvgScore() {

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_OVERALL_AVG_SCORE);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.next()) {
					double overallAvgScore = result.getDouble("overall_avg");
					return overallAvgScore;
				}
			}
		} catch (SQLException e) {
			System.out.println("searchScore Error : " + e);
		}
		return 0;
	}

	public int getScoreCount() {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.GET_SCORE_BY_COUNT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					return result.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.out.println("scoreCount error" + e);
		}
		return 0;
	}

	public boolean isScoreExistByStudentId(int studentId) {
		try (PreparedStatement preparedStatement = connection
			.prepareStatement(MySqlQuery.IS_SCORE_EXIST_BY_STUDENT_ID);) {
			preparedStatement.setInt(1, studentId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.next() == true) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println("isScoreExistById" + e);
			return false;
		}
	}

}
