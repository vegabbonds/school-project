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
import com.nts.school.dao.SubjectDao;
import com.nts.school.dao.TeacherDao;
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.object.Subject;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

	@Mock
	public SubjectDao subjectDao;

	@Mock
	public ScoreDao scoreDao;

	@Mock
	public TeacherDao teacherDao;

	@InjectMocks
	public SubjectService subjectService;

	@Test
	public void testAddSubject() {
		//given
		Subject subject = new Subject(12345, "math");

		//when		
		when(subjectDao.getSubjectCount()).thenReturn(1);
		when(subjectDao.addSubject((Subject)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = subjectService.addSubject(subject);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testAddMax() {
		//given
		Subject subject = new Subject(12345, "math");

		//when		
		when(subjectDao.getSubjectCount()).thenReturn(1000);
		when(subjectDao.addSubject((Subject)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = subjectService.addSubject(subject);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testModifySubjectSuccess() {
		//given
		Subject subject1 = new Subject(12345, "math");

		//when		
		when(subjectDao.modifySubject((Subject)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);

		ResultMessage message = subjectService.modifySubject(subject1);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testModifySubjectFail() {
		//given
		Subject subject1 = new Subject(12345, "math");

		//when		
		when(subjectDao.modifySubject((Subject)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);

		ResultMessage message = subjectService.modifySubject(subject1);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testDeleteSubjectSuccess() {
		//given
		int subjectId = 12345;

		//when
		when(scoreDao.deleteScoreBySubject(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.deleteTeacherBySubject(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(true);
		when(subjectDao.deleteSubject(anyInt())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = subjectService.deleteSubject(subjectId);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testDeleteSubjectFail_원인_score제거실패() {
		//given
		int subjectId = 12345;

		//when		
		when(scoreDao.deleteScoreBySubject(anyInt())).thenReturn(ResultMessage.DELETE_FAIL);

		ResultMessage message = subjectService.deleteSubject(subjectId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testDeleteSubjectFail_원인_teacher제거실패() {
		//given
		int subjectId = 12345;

		//when
		when(scoreDao.deleteScoreBySubject(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.deleteTeacherBySubject(anyInt())).thenReturn(ResultMessage.DELETE_FAIL);

		ResultMessage message = subjectService.deleteSubject(subjectId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testDeleteSubjectFail_원인_subjectId부재() {
		//given
		int subjectId = 12345;

		//when
		when(scoreDao.deleteScoreBySubject(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(teacherDao.deleteTeacherBySubject(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(subjectDao.isSubjectExistById(anyInt())).thenReturn(false);

		ResultMessage message = subjectService.deleteSubject(subjectId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testSearchSubject() {
		//given
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject1 = new Subject(12345, "math1");
		Subject subject2 = new Subject(12346, "math2");
		subjects.add(subject1);
		subjects.add(subject2);

		//when		
		when(subjectDao.searchSubject()).thenReturn(subjects);

		List<Subject> searchSubjects = new ArrayList<Subject>();
		searchSubjects = subjectService.searchSubject();

		//then
		assertThat(searchSubjects.size(), is(2));
		assertThat(searchSubjects.contains(subject1), is(true));
		assertThat(searchSubjects.contains(subject2), is(true));
	}

	@Test
	public void testSearchSpecificSubject() throws Exception {
		//given
		List<Subject> subjects = new ArrayList<Subject>();
		Subject subject1 = new Subject(12345, "math1");
		Subject subject2 = new Subject(12346, "math2");
		subjects.add(subject1);
		subjects.add(subject2);

		List<Subject> subjectsById = new ArrayList<Subject>();
		subjectsById.add(subject1);
		List<Subject> subjectsByName = new ArrayList<Subject>();
		subjectsByName.add(subject1);

		List<Subject> subjectsByNoneOption = new ArrayList<Subject>();

		//when		
		when(subjectDao.searchSubject()).thenReturn(subjects);
		when(subjectDao.searchSubjectById(anyInt())).thenReturn(subjectsById);
		when(subjectDao.searchSubjectByName(anyString())).thenReturn(subjectsByName);

		subjectsById = subjectService.searchSpecificSubject("1", "id");
		subjectsByName = subjectService.searchSpecificSubject("subject1", "name");
		subjectsByNoneOption = subjectService.searchSpecificSubject("19900304", " ");

		//then
		assertThat(subjectsById.size(), is(1));
		assertThat(subjectsByName.size(), is(1));
		assertThat(subjectsByNoneOption.size(), is(0));
		assertThat(subjectsById.contains(subject1), is(true));
		assertThat(subjectsByName.contains(subject1), is(true));
	}

}
