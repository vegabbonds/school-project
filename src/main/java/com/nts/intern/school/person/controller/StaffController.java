package com.nts.intern.school.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nts.intern.school.person.path.PersonViewPath;
import com.nts.intern.school.person.service.StaffService;
import com.nts.intern.school.person.vo.Staff;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.validation.ValidateUtil;

@Controller
@RequestMapping("/staff")
public class StaffController {
	@Autowired
	StaffService staffService;

	@Autowired
	ValidateUtil validateUtil;

	@RequestMapping("/add")
	public String addStaff(Staff staff, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkPersonValidation(staff) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = staffService.addStaff(staff);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/staff/search";
	}

	@RequestMapping("/delete")
	public String deleteStaff(int id, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkStaffIdValidation(id) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = staffService.deleteStaff(id);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/staff/search";
	}

	@RequestMapping("/modify")
	public String modifyStaff(Staff staff, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkPersonValidation(staff) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = staffService.modifyStaff(staff);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/staff/search";
	}

	@RequestMapping("/search")
	public String searchStaffs(Model model) {
		List<Staff> staffs = staffService.searchStaffs();
		model.addAttribute("staffs", staffs);
		return PersonViewPath.STAFF_PAGE.getPath();
	}

	@RequestMapping("/search/specific")
	public String searchSpecificStaffs(String sel_opt, String search_value, Model model,
			RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSearchSpecificInputValidation(sel_opt, search_value) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/staff/search";
		}

		List<Staff> staffs = staffService.searchSpecificStaffs(sel_opt, search_value);
		model.addAttribute("staffs", staffs);
		return PersonViewPath.STAFF_PAGE.getPath();
	}
}
