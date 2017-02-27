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

import com.nts.school.dao.StaffDao;
import com.nts.school.util.ResultMessage;
import com.nts.school.vo.person.Staff;

@RunWith(MockitoJUnitRunner.class)
public class StaffServiceTest {

	@Mock
	public StaffDao staffDao;

	@InjectMocks
	public StaffService staffService;

	@Test
	public void testAddStaff() {
		//given
		Staff staff = new Staff(1234567, "jeong", "19900302");

		//when		
		when(staffDao.getStaffCount()).thenReturn(1);
		when(staffDao.addStaff((Staff)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage resultMessage = staffService.addStaff(staff);

		//then
		assertThat(resultMessage, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testAddMax() {
		//given
		Staff staff = new Staff(1234567, "jeong", "19900302");

		//when		
		when(staffDao.getStaffCount()).thenReturn(1000);
		when(staffDao.addStaff((Staff)anyObject())).thenReturn(ResultMessage.SUCCESS);

		ResultMessage message = staffService.addStaff(staff);

		//then
		assertThat(message, is(ResultMessage.ADD_FAIL));
	}

	@Test
	public void testModifyStaffSuccess() {
		//given
		Staff staff1 = new Staff(1234567, "jeong", "19900302");

		//when		
		when(staffDao.modifyStaff((Staff)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(staffDao.isStaffExistById(anyInt())).thenReturn(true);

		ResultMessage message = staffService.modifyStaff(staff1);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testModifyStaffFail() {
		//given
		Staff staff1 = new Staff(1234567, "jeong", "19900302");

		//when		
		when(staffDao.modifyStaff((Staff)anyObject())).thenReturn(ResultMessage.SUCCESS);
		when(staffDao.isStaffExistById(anyInt())).thenReturn(false);

		ResultMessage message = staffService.modifyStaff(staff1);

		//then
		assertThat(message, is(ResultMessage.MODIFY_FAIL));
	}

	@Test
	public void testDeleteStaffSuccess() {
		//given
		int staffId = 1234567;

		//when		
		when(staffDao.deleteStaff(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(staffDao.isStaffExistById(anyInt())).thenReturn(true);

		ResultMessage message = staffService.deleteStaff(staffId);

		//then
		assertThat(message, is(ResultMessage.SUCCESS));
	}

	@Test
	public void testDeleteStaffFail() {
		//given
		int staffId = 1234567;

		//when		
		when(staffDao.deleteStaff(anyInt())).thenReturn(ResultMessage.SUCCESS);
		when(staffDao.isStaffExistById(anyInt())).thenReturn(false);

		ResultMessage message = staffService.deleteStaff(staffId);

		//then
		assertThat(message, is(ResultMessage.DELETE_FAIL));
	}

	@Test
	public void testSearchStaff() {
		//given
		List<Staff> staffs = new ArrayList<Staff>();
		Staff staff1 = new Staff(1234568, "staff1", "19900302");
		Staff staff2 = new Staff(1234569, "staff1", "19900302");
		staffs.add(staff1);
		staffs.add(staff2);

		//when		
		when(staffDao.searchStaff()).thenReturn(staffs);

		List<Staff> searchStaffs = new ArrayList<Staff>();
		searchStaffs = staffService.searchStaff();

		//then
		assertThat(searchStaffs.size(), is(2));
		assertThat(searchStaffs.contains(staff1), is(true));
		assertThat(searchStaffs.contains(staff2), is(true));
	}

	@Test
	public void testSearchSpecificStaff() throws Exception {
		//given
		List<Staff> staffs = new ArrayList<Staff>();
		Staff staff1 = new Staff(1234568, "staff1", "19900302");
		Staff staff2 = new Staff(1234569, "staff1", "19900302");
		staffs.add(staff1);
		staffs.add(staff2);

		List<Staff> staffsById = new ArrayList<Staff>();
		staffsById.add(staff1);
		List<Staff> staffsByName = new ArrayList<Staff>();
		staffsByName.add(staff1);

		List<Staff> staffsByNoneOption = new ArrayList<Staff>();

		//when		
		when(staffDao.searchStaff()).thenReturn(staffs);
		when(staffDao.searchStaffById(anyInt())).thenReturn(staffsById);
		when(staffDao.searchStaffByName(anyString())).thenReturn(staffsByName);

		staffsById = staffService.searchSpecificStaff("1", "id");
		staffsByName = staffService.searchSpecificStaff("staff1", "name");
		staffsByNoneOption = staffService.searchSpecificStaff("19900304", " ");

		//then
		assertThat(staffsById.size(), is(1));
		assertThat(staffsByName.size(), is(1));
		assertThat(staffsByNoneOption.size(), is(0));
		assertThat(staffsById.contains(staff1), is(true));
		assertThat(staffsByName.contains(staff1), is(true));
	}

}
