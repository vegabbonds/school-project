package com.nts.intern.school.person.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.nts.intern.school.person.vo.Staff;

@Mapper
public interface StaffDao {
	public Integer addStaff(Staff staff) throws Exception;

	public Integer deleteStaff(int id);

	public Integer modifyStaff(Staff staff);

	public List<Staff> searchStaffs();

	public List<Staff> searchStaffsById(int id);

	public List<Staff> searchStaffsByName(String name);

	public Integer isStaffExistById(int id);

	public Integer getStaffCount();
}
