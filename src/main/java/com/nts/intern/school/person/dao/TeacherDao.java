package com.nts.intern.school.person.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.intern.school.person.vo.Teacher;

@Mapper
public interface TeacherDao {
	public Integer addTeacher(Teacher teacher) throws Exception;

	public Integer deleteTeacher(int id);

	public Integer deleteTeacherBySubject(int subjectId);

	public Integer modifyTeacher(Teacher teacher);

	public List<Teacher> searchTeachers();

	public List<Teacher> searchTeachersById(int id);

	public List<Teacher> searchTeachersByName(String name);

	public Integer isTeacherExistById(int id);

	public Integer getTeacherCount();
}
