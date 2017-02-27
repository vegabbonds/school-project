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

import com.nts.school.dao.SubjectDao;
import com.nts.school.dao.TeacherDao;
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.person.Teacher;

@RunWith(MockitoJUnitRunner.class)
public class TeacherServiceTest {

	@Mock
	public TeacherDao teacherDao;

	@Mock
	public SubjectDao subjectDao;

	@InjectMocks
	public TeacherService teacherService;

	@Test
	public void testAddTeacherSuccess() {
		//given
		Teacher teacher = new Teacher(123456, "jeong", "19900302", 12345);

		//when		
		when(teacherDao.getTeacherCount()).thenReturn(1);
		when(teacherDao.addTeacher((Teacher)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);

		ResultMessage message = teacherService.addTeacher(teacher);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testAddTeacherFail() {
		//given
		Teacher teacher = new Teacher(123456, "jeong", "19900302", 12345);

		//when		
		when(teacherDao.getTeacherCount()).thenReturn(1);
		when(teacherDao.addTeacher((Teacher)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);

		ResultMessage message = teacherService.addTeacher(teacher);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testAddMax() {
		//given
		Teacher teacher = new Teacher(123456, "jeong", "19900302", 12345);

		//when		
		when(teacherDao.getTeacherCount()).thenReturn(1000);
		when(teacherDao.addTeacher((Teacher)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = teacherService.addTeacher(teacher);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testModifyTeacherSuccess() {
		//given
		Teacher teacher1 = new Teacher(123456, "jeong", "19900302", 12345);

		//when		
		when(teacherDao.modifyTeacher((Teacher)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.isTeacherExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);

		ResultMessage message = teacherService.modifyTeacher(teacher1);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testModifyTeacherFail_원인_teacherId부재() {
		//given
		Teacher teacher1 = new Teacher(123456, "jeong", "19900302", 12345);

		//when		
		when(teacherDao.modifyTeacher((Teacher)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.isTeacherExistById(anyInt())).thenReturn(false);

		ResultMessage message = teacherService.modifyTeacher(teacher1);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testModifyTeacherFail_원인_subjectId부재() {
		//given
		Teacher teacher1 = new Teacher(123456, "jeong", "19900302", 12345);

		//when		
		when(teacherDao.modifyTeacher((Teacher)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.isTeacherExistById(anyInt())).thenReturn(true);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);

		ResultMessage message = teacherService.modifyTeacher(teacher1);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testDeleteTeacherSuccess() {
		//given
		int teacherId = 123456;

		//when		
		when(teacherDao.deleteTeacher(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.isTeacherExistById(anyInt())).thenReturn(true);

		ResultMessage message = teacherService.deleteTeacher(teacherId);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testDeleteTeacherFail() {
		//given
		int teacherId = 123456;

		//when		
		when(teacherDao.deleteTeacher(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.isTeacherExistById(anyInt())).thenReturn(false);

		ResultMessage message = teacherService.deleteTeacher(teacherId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testSearchTeacher() {
		//given
		List<Teacher> teachers = new ArrayList<Teacher>();
		Teacher teacher1 = new Teacher(123456, "teacher1", "19900302", 12345);
		Teacher teacher2 = new Teacher(123457, "teacher1", "19900302", 12345);
		teachers.add(teacher1);
		teachers.add(teacher2);

		//when		
		when(teacherDao.searchTeacher()).thenReturn(teachers);

		List<Teacher> searchTeachers = new ArrayList<Teacher>();
		searchTeachers = teacherService.searchTeacher();

		//then
		assertThat(searchTeachers.size(), is(2));
		assertThat(searchTeachers.contains(teacher1), is(true));
		assertThat(searchTeachers.contains(teacher2), is(true));
	}

	@Test
	public void testSearchSpecificTeacher() throws Exception {
		//given
		List<Teacher> teachers = new ArrayList<Teacher>();
		Teacher teacher1 = new Teacher(123456, "teacher1", "19900302", 12345);
		Teacher teacher2 = new Teacher(123457, "teacher1", "19900302", 12345);
		teachers.add(teacher1);
		teachers.add(teacher2);

		List<Teacher> teachersById = new ArrayList<Teacher>();
		teachersById.add(teacher1);
		List<Teacher> teachersByName = new ArrayList<Teacher>();
		teachersByName.add(teacher1);

		List<Teacher> teachersByNoneOption = new ArrayList<Teacher>();

		//when		
		when(teacherDao.searchTeacher()).thenReturn(teachers);
		when(teacherDao.searchTeacherById(anyInt())).thenReturn(teachersById);
		when(teacherDao.searchTeacherByName(anyString())).thenReturn(teachersByName);

		teachersById = teacherService.searchSpecificTeacher("1", "id");
		teachersByName = teacherService.searchSpecificTeacher("teacher1", "name");
		teachersByNoneOption = teacherService.searchSpecificTeacher("19900304", " ");

		//then
		assertThat(teachersById.size(), is(1));
		assertThat(teachersByName.size(), is(1));
		assertThat(teachersByNoneOption.size(), is(0));
		assertThat(teachersById.contains(teacher1), is(true));
		assertThat(teachersByName.contains(teacher1), is(true));
	}

}
