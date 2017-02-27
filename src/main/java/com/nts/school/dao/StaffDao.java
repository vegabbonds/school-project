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
import com.nts.school.vo.person.Staff;

/**
 * 서비스에서 데이터 베이스에 접근을 할 때
 * Dao를 통해서 접근을 하도록 하였다.
 * @author 이정석
 */
public class StaffDao {

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

	public ResultMessage addStaff(Staff staff) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.ADD_STAFF);) {
			preparedStatement.setInt(1, staff.getId());
			preparedStatement.setString(2, staff.getName());
			preparedStatement.setString(3, staff.getBirthDate());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("addStaff Error : " + e);
			return ResultMessage.ADD_FAIL;
		}

	}

	public ResultMessage modifyStaff(Staff staff) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.MODIFY_STAFF);) {
			preparedStatement.setString(1, staff.getName());
			preparedStatement.setString(2, staff.getBirthDate());
			preparedStatement.setInt(3, staff.getId());

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("modifyStaff Error : " + e);
			return ResultMessage.MODIFY_FAIL;
		}
	}

	public ResultMessage deleteStaff(int staffId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.DELETE_STAFF);) {
			preparedStatement.setInt(1, staffId);

			preparedStatement.executeUpdate();
			return ResultMessage.SUCCESS;

		} catch (SQLException e) {
			System.out.println("deleteStaff Error : " + e);
			return ResultMessage.DELETE_FAIL;
		}
	}

	public List<Staff> searchStaff() {
		List<Staff> staffs = new ArrayList<Staff>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_STAFF);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("staff_id");
					String name = result.getString("staff_name");
					String birthDate = result.getString("staff_birth");
					Staff staff = new Staff(id, name, birthDate);

					staffs.add(staff);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchStaff Error : " + e);
		}

		return staffs;
	}

	public List<Staff> searchStaffById(int staffId) {
		List<Staff> staffs = new ArrayList<Staff>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_STAFF_BY_ID);) {
			preparedStatement.setInt(1, staffId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("staff_id");
					String name = result.getString("staff_name");
					String birthDate = result.getString("staff_birth");
					Staff staff = new Staff(id, name, birthDate);

					staffs.add(staff);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificIdStaff Error : " + e);
		}

		return staffs;
	}

	public List<Staff> searchStaffByName(String staffName) {
		List<Staff> staffs = new ArrayList<Staff>();

		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.SEARCH_STAFF_BY_NAME);) {
			preparedStatement.setString(1, staffName);

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					int id = result.getInt("staff_id");
					String name = result.getString("staff_name");
					String birthDate = result.getString("staff_birth");
					Staff staff = new Staff(id, name, birthDate);

					staffs.add(staff);
				}
			}
		} catch (SQLException e) {
			System.out.println("searchSpecificNameStaff Error : " + e);
		}

		return staffs;
	}

	public int getStaffCount() {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.GET_STAFF_BY_COUNT);) {

			try (ResultSet result = preparedStatement.executeQuery();) {
				while (result.next()) {
					return result.getInt("count");
				}
			}
		} catch (SQLException e) {
			System.out.println("staffCount error" + e);
		}
		return 0;
	}

	public boolean isStaffExistById(int staffId) {
		try (PreparedStatement preparedStatement = connection.prepareStatement(MySqlQuery.IS_STAFF_EXIST_BY_ID);) {
			preparedStatement.setInt(1, staffId);

			try (ResultSet result = preparedStatement.executeQuery();) {
				if (result.next() == true) {
					return true;
				}
				return false;
			}
		} catch (SQLException e) {
			System.out.println("isStaffExistById" + e);
			return false;
		}
	}
}
