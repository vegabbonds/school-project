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
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.person.Student;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {

	@Mock
	public StudentDao studentDao;

	@Mock
	public ScoreDao scoreDao;

	@InjectMocks
	public StudentService studentService;

	@Test
	public void testAddStudent() {
		//given
		Student student = new Student(12345678, "jeong", "19900302");

		//when		
		when(studentDao.getStudentCount()).thenReturn(0);
		when(studentDao.addStudent((Student)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = studentService.addStudent(student);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testAddMax() {
		//given
		Student student = new Student(12345678, "jeong", "19900302");

		//when		
		when(studentDao.getStudentCount()).thenReturn(1000);
		when(studentDao.addStudent((Student)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = studentService.addStudent(student);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testModifyStudentSuccess() {
		//given
		Student student1 = new Student(12345678, "jeong", "19900302");

		//when		
		when(studentDao.modifyStudent((Student)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);

		ResultMessage message = studentService.modifyStudent(student1);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testModifyStudentFail() {
		//given
		Student student1 = new Student(12345678, "jeong", "19900302");

		//when		
		when(studentDao.modifyStudent((Student)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(studentDao.isStudentExistById(anyInt())).thenReturn(false);

		ResultMessage message = studentService.modifyStudent(student1);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testDeleteStudentSuccess() {
		//given
		int studentId = 12345678;

		//when				
		when(scoreDao.isScoreExistByStudentId(anyInt())).thenReturn(true);
		when(scoreDao.deleteScoreByStudent(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(studentDao.isStudentExistById(anyInt())).thenReturn(true);
		when(studentDao.deleteStudent(anyInt())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = studentService.deleteStudent(studentId);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testDeleteStudentFail_원인_학생부재() {
		//given
		int studentId = 12345678;

		//when		
		when(studentDao.deleteStudent(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(studentDao.isStudentExistById(anyInt())).thenReturn(false);

		ResultMessage message = studentService.deleteStudent(studentId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testDeleteStudentFail_원인_성적_학생제거실패() {
		//given
		int studentId = 12345678;

		//when
		when(scoreDao.isScoreExistByStudentId(anyInt())).thenReturn(true);
		when(scoreDao.deleteScoreByStudent(anyInt())).thenReturn(ResultMessage.DELETE_FAIL);

		ResultMessage message = studentService.deleteStudent(studentId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testSearchStudent() {
		//given
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student(12345678, "student1", "19900302");
		Student student2 = new Student(12345679, "student1", "19900302");
		students.add(student1);
		students.add(student2);

		//when		
		when(studentDao.searchStudent()).thenReturn(students);

		List<Student> searchStudents = new ArrayList<Student>();
		searchStudents = studentService.searchStudent();

		//then
		assertThat(searchStudents.size(), is(2));
		assertThat(searchStudents.contains(student1), is(true));
		assertThat(searchStudents.contains(student2), is(true));
	}

	@Test
	public void testSearchSpecificStudent() throws Exception {
		//given
		List<Student> students = new ArrayList<Student>();
		Student student1 = new Student(12345678, "student1", "19900302");
		Student student2 = new Student(12345679, "student1", "19900302");
		students.add(student1);
		students.add(student2);

		List<Student> studentsById = new ArrayList<Student>();
		studentsById.add(student1);
		List<Student> studentsByName = new ArrayList<Student>();
		studentsByName.add(student1);

		List<Student> studentsByNoneOption = new ArrayList<Student>();

		//when		
		when(studentDao.searchStudent()).thenReturn(students);
		when(studentDao.searchStudentById(anyInt())).thenReturn(studentsById);
		when(studentDao.searchStudentByName(anyString())).thenReturn(studentsByName);

		studentsById = studentService.searchSpecificStudent("1", "id");
		studentsByName = studentService.searchSpecificStudent("student1", "name");
		studentsByNoneOption = studentService.searchSpecificStudent("19900304", " ");

		//then
		assertThat(studentsById.size(), is(1));
		assertThat(studentsByName.size(), is(1));
		assertThat(studentsByNoneOption.size(), is(0));
		assertThat(studentsById.contains(student1), is(true));
		assertThat(studentsByName.contains(student1), is(true));
	}

}
