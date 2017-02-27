package com.nts.intern.school.person.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.intern.school.person.vo.Student;

@Mapper
public interface StudentDao {
	public Integer addStudent(Student student) throws Exception;

	public Integer deleteStudent(int id);

	public Integer modifyStudent(Student student);

	public List<Student> searchStudents();

	public List<Student> searchStudentsById(int id);

	public List<Student> searchStudentsByName(String name);

	public Integer isStudentExistById(int id);

	public Integer getStudentCount();	
}
