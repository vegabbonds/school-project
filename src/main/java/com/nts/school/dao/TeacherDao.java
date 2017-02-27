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
import com.nts.school.vo.person.Teacher;

/**
 * 서비스에서 데이터 베이스에 접근을 할 때
 * Dao를 통해서 접근을 하도록 하였다.
 * @author 이정석
 */
public class TeacherDao {

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

	public ResultMessage addTeacher(Teacher teacher) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.ADD_TEACHER);) {
			preparedStatement.setInt(1, teacher.getId());
			preparedStatement.setString(2, teacher.getName());
			preparedStatement.setString(3, teacher.getBirthDate());
			preparedStatement.setInt(4, teacher.getSubjectId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("addTeacher Error : " + e);
			return ResultMessage.ADD_FAIL;
		}

	}

	public ResultMessage modifyTeacher(Teacher teacher) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.MODIFY_TEACHER);) {
			preparedStatement.setString(1, teacher.getName());
			preparedStatement.setString(2, teacher.getBirthDate());
			preparedStatement.setInt(3, teacher.getSubjectId());
			preparedStatement.setInt(4, teacher.getId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("modifyTeacher Error : " + e);
			return ResultMessage.MODIFY_FAIL;
		}
	}

	public ResultMessage deleteTeacher(int teacherId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_TEACHER);) {
			preparedStatement.setInt(1, teacherId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteTeacher Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public ResultMessage deleteTeacherBySubject(int subjectId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_TEACHER_BY_SUBJECT);) {
			preparedStatement.setInt(1, subjectId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteTeacher Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public List<Teacher> searchTeacher() {
		List<Teacher> teachers = new ArrayList<Teacher>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_TEACHER);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("teacher_id");
					String name = result.getString("teacher_name");
					String birthDate = result.getString("teacher_birth");
					int subjectId = result.getInt("subject_id");
					Teacher teacher = new Teacher(id, name, birthDate, subjectId);

					teachers.add(teacher);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchTeacher Error : " + e);
		}

		return teachers;
	}

	public List<Teacher> searchTeacherById(int teacherId) {
		List<Teacher> teachers = new ArrayList<Teacher>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_TEACHER_BY_ID);) {
			preparedStatement.setInt(1, teacherId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("teacher_id");
					String name = result.getString("teacher_name");
					String birthDate = result.getString("teacher_birth");
					int subjectId = result.getInt("subject_id");
					Teacher teacher = new Teacher(id, name, birthDate, subjectId);

					teachers.add(teacher);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificIdTeacher Error : " + e);
		}

		return teachers;
	}

	public List<Teacher> searchTeacherByName(String teacherName) {
		List<Teacher> teachers = new ArrayList<Teacher>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_TEACHER_BY_NAME);) {
			preparedStatement.setString(1, teacherName);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("teacher_id");
					String name = result.getString("teacher_name");
					String birthDate = result.getString("teacher_birth");
					int subjectId = result.getInt("subject_id");
					Teacher teacher = new Teacher(id, name, birthDate, subjectId);

					teachers.add(teacher);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificNameTeacher Error : " + e);
		}

		return teachers;
	}

	public int getTeacherCount() {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.GET_TEACHER_BY_COUNT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					return result.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.out.println("teacherCount error" + e);
		}
		return 0;
	}

	public boolean isTeacherExistById(int teacherId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.IS_TEACHER_EXIST_BY_ID);) {
			preparedStatement.setInt(1, teacherId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.next() == true) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println("isTeacherExistById" + e);
			return false;
		}
	}
}
