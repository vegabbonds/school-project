package com.nts.intern.school.person.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.intern.school.person.constant.PersonConstant;
import com.nts.intern.school.person.dao.StaffDao;
import com.nts.intern.school.person.vo.Staff;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.option.SearchOption;

@Service
public class StaffService {
	@Autowired
	StaffDao staffDao;

	public ResultMessage addStaff(Staff staff) {
		if (staffDao.getStaffCount() >= PersonConstant.MAX_PERSON) {
			return ResultMessage.ADD_FAIL;
		}

		try {
			staffDao.addStaff(staff);
			return ResultMessage.SUCCESS;
		} catch (Exception e) {
			return ResultMessage.ADD_FAIL;
		}
	}

	public ResultMessage deleteStaff(int id) {
		if (staffDao.deleteStaff(id) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.DELETE_FAIL;
	}

	public ResultMessage modifyStaff(Staff staff) {
		if (staffDao.modifyStaff(staff) > 0) {
			return ResultMessage.SUCCESS;
		}

		return ResultMessage.MODIFY_FAIL;
	}

	public List<Staff> searchStaffs() {
		return staffDao.searchStaffs();
	}

	public List<Staff> searchSpecificStaffs(String selectOption, String searchValue) {
		SearchOption option = SearchOption.findSearchOption(selectOption);
		switch (option) {
			case ID:
				return searchStaffsById(searchValue);
			case NAME:
				return searchStaffsByName(searchValue);
			default:
				return new ArrayList<Staff>();
		}
	}

	public List<Staff> searchStaffsById(String searchValue) {
		return staffDao.searchStaffsById(Integer.parseInt(searchValue));
	}

	public List<Staff> searchStaffsByName(String searchValue) {
		return staffDao.searchStaffsByName(searchValue);
	}
}
