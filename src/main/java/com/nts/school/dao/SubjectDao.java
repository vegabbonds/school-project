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
import com.nts.school.vo.object.Subject;

/**
 * 서비스에서 데이터 베이스에 접근을 할 때
 * Dao를 통해서 접근을 하도록 하였다.
 * @author 이정석
 */
public class SubjectDao {

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

	public ResultMessage addSubject(Subject subject) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.ADD_SUBJECT);) {
			preparedStatement.setInt(1, subject.getSubjectId());
			preparedStatement.setString(2, subject.getSubjectName());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("addSubject Error : " + e);
			return ResultMessage.ADD_FAIL;
		}

	}

	public ResultMessage modifySubject(Subject subject) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.MODIFY_SUBJECT);) {
			preparedStatement.setString(1, subject.getSubjectName());
			preparedStatement.setInt(2, subject.getSubjectId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("modifySubject Error : " + e);
			return ResultMessage.MODIFY_FAIL;
		}
	}

	public ResultMessage deleteSubject(int subjectId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_SUBJECT);) {
			preparedStatement.setInt(1, subjectId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteSubject Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public List<Subject> searchSubject() {
		List<Subject> subjects = new ArrayList<Subject>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_SUBJECT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("subject_id");
					String name = result.getString("subject_name");
					Subject subject = new Subject(id, name);

					subjects.add(subject);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSubject Error : " + e);
		}

		return subjects;
	}

	public List<Subject> searchSubjectById(int subjectId) {
		List<Subject> subjects = new ArrayList<Subject>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_SUBJECT_BY_ID);) {
			preparedStatement.setInt(1, subjectId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("subject_id");
					String name = result.getString("subject_name");
					Subject subject = new Subject(id, name);

					subjects.add(subject);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificIdSubject Error : " + e);
		}

		return subjects;
	}

	public List<Subject> searchSubjectByName(String subjectName) {
		List<Subject> subjects = new ArrayList<Subject>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_SUBJECT_BY_NAME);) {
			preparedStatement.setString(1, subjectName);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("subject_id");
					String name = result.getString("subject_name");
					Subject subject = new Subject(id, name);

					subjects.add(subject);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificNameSubject Error : " + e);
		}

		return subjects;
	}

	public int getSubjectCount() {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.GET_SUBJECT_BY_COUNT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					return result.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.out.println("subjectCount error" + e);
		}
		return 0;
	}

	public boolean isSubjectExistById(int subjectId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.IS_SUBJECT_EXIST_BY_ID);) {
			preparedStatement.setInt(1, subjectId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.next() == true) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println("isSubjectExistById" + e);
			return false;
		}
	}
}
