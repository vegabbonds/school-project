package com.nts.school.util;

/**
 * 필요한 상수를 저장해 놓은 class
 * @author 이정석
 */
public class Constant {
	public static final int MAX_PERSON = 1000;
	public static final int MAX_SUBJECT_LENGTH = 10;
	public static final int MIN_ID = 0;
	public static final int MAX_ID = 9999;
	public static final int MIN_BIRTHDATE = 1900_0000;
	public static final int MAX_BIRTHDATE = 2100_0000;

	public static final int MIN_STUDENT_ID = 10000000;
	public static final int MAX_STUDENT_ID = 99999999;
	public static final int MIN_STAFF_ID = 1000000;
	public static final int MAX_STAFF_ID = 9999999;
	public static final int MIN_TEACHER_ID = 100000;
	public static final int MAX_TEACHER_ID = 999999;
	public static final int MIN_SUBJECT_ID = 10000;
	public static final int MAX_SUBJECT_ID = 99999;
	public static final int MIN_SCORE = 0;
	public static final int MAX_SCORE = 100;
	public static final int MIN_NAME_LENGTH = 1;
	public static final int MAX_NAME_LENGTH = 10;

	public final static String STUDENT_FILENAME = "student";
	public final static String STAFF_FILENAME = "staff";
	public final static String TEACHER_FILENAME = "teacher";
	public final static String SUBJECT_FILENAME = "subject";
	public final static String STUDENTSCORE_FILENAME = "studentScore";

	public final static int STUDENT_FILE_FORMAT_LENGTH = 3;
	public final static int STAFF_FILE_FORMAT_LENGTH = 3;
	public final static int TEACHER_FILE_FORMAT_LENGTH = 4;
	public final static int SUBJECT_FILE_FORMAT_LENGTH = 2;
	public final static int STUDENTSCORE_FILE_FORMAT_LENGTH = 3;

	public static final String CONNECTION_URL = "jdbc:mysql://127.0.0.1:3306/dbschool";
	public static final String ID = "jeong";
	public static final String PASSWORD = "jeong";

}
