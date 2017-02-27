package com.nts.school.util.query;

public class MySqlQuery {
	public static String ADD_STAFF = "INSERT INTO staff VALUES(?, ?, ?)";
	public static String MODIFY_STAFF = "UPDATE staff SET staff_name = ?, staff_birth = ? WHERE staff_id = ?";
	public static String DELETE_STAFF = "DELETE FROM staff WHERE staff_id = ?";
	public static String SEARCH_STAFF = "SELECT * FROM staff";
	public static String SEARCH_STAFF_BY_ID = "SELECT * FROM staff WHERE staff_id = ?";
	public static String SEARCH_STAFF_BY_NAME = "SELECT * FROM staff WHERE staff_name = ?";
	public static String GET_STAFF_BY_COUNT = "SELECT COUNT(1) AS 'count' FROM staff";
	public static String IS_STAFF_EXIST_BY_ID = "SELECT * FROM staff WHERE staff_id = ?";

	public static String ADD_STUDENT = "INSERT INTO student VALUES(?, ?, ?)";
	public static String MODIFY_STUDENT = "UPDATE student SET student_name = ?, student_birth = ? WHERE student_id = ?";
	public static String DELETE_STUDENT = "DELETE FROM student WHERE student_id = ?";
	public static String SEARCH_STUDENT = "SELECT * FROM student";
	public static String SEARCH_STUDENT_BY_ID = "SELECT * FROM student WHERE student_id = ?";
	public static String SEARCH_STUDENT_BY_NAME = "SELECT * FROM student WHERE student_name = ?";
	public static String GET_STUDENT_BY_COUNT = "SELECT COUNT(1) AS 'count' FROM student";
	public static String IS_STUDENT_EXIST_BY_ID = "SELECT * FROM student WHERE student_id = ?";

	public static String ADD_SUBJECT = "INSERT INTO subject VALUES(?, ?)";
	public static String MODIFY_SUBJECT = "UPDATE subject SET subject_name = ? WHERE subject_id = ?";
	public static String DELETE_SUBJECT = "DELETE FROM subject WHERE subject_id = ?";
	public static String SEARCH_SUBJECT = "SELECT * FROM subject";
	public static String SEARCH_SUBJECT_BY_ID = "SELECT * FROM subject WHERE subject_id = ?";
	public static String SEARCH_SUBJECT_BY_NAME = "SELECT * FROM subject WHERE subject_name = ?";
	public static String GET_SUBJECT_BY_COUNT = "SELECT COUNT(1) AS 'count' FROM subject";
	public static String IS_SUBJECT_EXIST_BY_ID = "SELECT * FROM subject WHERE subject_id = ?";

	public static String ADD_TEACHER = "INSERT INTO teacher VALUES(?, ?, ?, ?)";
	public static String MODIFY_TEACHER = "UPDATE teacher SET teacher_name = ?, teacher_birth = ?, subject_id = ? WHERE teacher_id = ?";
	public static String DELETE_TEACHER = "DELETE FROM teacher WHERE teacher_id = ?";
	public static String DELETE_TEACHER_BY_SUBJECT = "DELETE FROM teacher WHERE subject_id = ?";
	public static String SEARCH_TEACHER = "SELECT * FROM teacher";
	public static String SEARCH_TEACHER_BY_ID = "SELECT * FROM teacher WHERE teacher_id = ?";
	public static String SEARCH_TEACHER_BY_NAME = "SELECT * FROM teacher WHERE teacher_name = ?";
	public static String GET_TEACHER_BY_COUNT = "SELECT COUNT(1) AS 'count' FROM teacher";
	public static String IS_TEACHER_EXIST_BY_ID = "SELECT * FROM teacher WHERE teacher_id = ?";

	public static String ADD_SCORE = "INSERT INTO score VALUES(?, ?, ?)";
	public static String MODIFY_SCORE = "UPDATE score SET score = ? WHERE student_id = ? and subject_id = ?";
	public static String DELETE_SCORE = "DELETE FROM score WHERE student_id = ? and subject_id = ?";
	public static String DELETE_SCORE_BY_STUDENT = "DELETE FROM score WHERE student_id = ?";
	public static String DELETE_SCORE_BY_SUBJECT = "DELETE FROM score WHERE subject_id = ?";
	public static String SEARCH_SCORE = "SELECT student.student_id, student_name, subject.subject_id, subject.subject_name, score.score "
		+ "FROM student student, subject subject, score score "
		+ "WHERE score.student_id = student.student_id AND score.subject_id = subject.subject_id";
	public static String SEARCH_SCORE_BY_STUDENT_ID = SEARCH_SCORE + " AND student.student_id = ?";
	public static String SEARCH_AVG_SCORE_BY_STUDENT = "SELECT student.student_id, student.student_name, AVG(score) as avg "
		+ "FROM student student, score score "
		+ "WHERE score.student_id = student.student_id GROUP BY student.student_id";
	public static String SEARCH_AVG_SCORE_BY_SPECIFIC_STUDENT = SEARCH_AVG_SCORE_BY_STUDENT
		+ " HAVING student.student_id = ?";
	public static String SEARCH_OVERALL_AVG_SCORE = "SELECT AVG(a.avg) AS overall_avg " + "FROM ("
		+ SEARCH_AVG_SCORE_BY_STUDENT + ") a";
	public static String GET_SCORE_BY_COUNT = "SELECT COUNT(1) AS 'count' FROM score";
	public static String IS_SCORE_EXIST_BY_STUDENT_ID = "SELECT * FROM score WHERE student_id = ?";
}
