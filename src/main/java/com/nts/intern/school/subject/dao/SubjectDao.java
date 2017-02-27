package com.nts.intern.school.subject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.intern.school.subject.vo.Subject;

@Mapper
public interface SubjectDao {
	public Integer addSubject(Subject subject) throws Exception;;

	public Integer deleteSubject(int id);

	public Integer modifySubject(Subject subject);

	public List<Subject> searchSubjects();

	public List<Subject> searchSubjectsById(int id);

	public List<Subject> searchSubjectsByName(String name);

	public Integer isSubjectExistById(int id);

	public Integer getSubjectCount();
}
