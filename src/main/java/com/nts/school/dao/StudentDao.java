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
import com.nts.school.vo.person.Student;

/**
 * 서비스에서 데이터 베이스에 접근을 할 때
 * Dao를 통해서 접근을 하도록 하였다.
 * @author 이정석
 */
public class StudentDao {

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

	public ResultMessage addStudent(Student student) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.ADD_STUDENT);) {
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getBirthDate());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("addStudent Error : " + e);
			return ResultMessage.ADD_FAIL;
		}

	}

	public ResultMessage modifyStudent(Student student) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.MODIFY_STUDENT);) {
			preparedStatement.setString(1, student.getName());
			preparedStatement.setString(2, student.getBirthDate());
			preparedStatement.setInt(3, student.getId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("modifyStudent Error : " + e);
			return ResultMessage.MODIFY_FAIL;
		}
	}

	public ResultMessage deleteStudent(int studentId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_STUDENT);) {
			preparedStatement.setInt(1, studentId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteStudent Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public List<Student> searchStudent() {
		List<Student> students = new ArrayList<Student>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_STUDENT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("student_id");
					String name = result.getString("student_name");
					String birthDate = result.getString("student_birth");
					Student student = new Student(id, name, birthDate);

					students.add(student);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchStudent Error : " + e);
		}

		return students;
	}

	public List<Student> searchStudentById(int studentId) {
		List<Student> students = new ArrayList<Student>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_STUDENT_BY_ID);) {
			preparedStatement.setInt(1, studentId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("student_id");
					String name = result.getString("student_name");
					String birthDate = result.getString("student_birth");
					Student student = new Student(id, name, birthDate);

					students.add(student);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificIdStudent Error : " + e);
		}

		return students;
	}

	public List<Student> searchStudentByName(String studentName) {
		List<Student> students = new ArrayList<Student>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_STUDENT_BY_NAME);) {
			preparedStatement.setString(1, studentName);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("student_id");
					String name = result.getString("student_name");
					String birthDate = result.getString("student_birth");
					Student student = new Student(id, name, birthDate);

					students.add(student);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificNameStudent Error : " + e);
		}

		return students;
	}

	public int getStudentCount() {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.GET_STUDENT_BY_COUNT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					return result.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.out.println("studentCount error" + e);
		}
		return 0;
	}

	public boolean isStudentExistById(int studentId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.IS_STUDENT_EXIST_BY_ID);) {
			preparedStatement.setInt(1, studentId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.next() == true) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println("isStudentExistById" + e);
			return false;
		}
	}
}
