package com.nts.intern.school.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nts.intern.school.person.path.PersonViewPath;
import com.nts.intern.school.person.service.TeacherService;
import com.nts.intern.school.person.vo.Teacher;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.validation.ValidateUtil;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
	@Autowired
	TeacherService teacherService;

	@Autowired
	ValidateUtil validateUtil;

	@RequestMapping("/add")
	public String addTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkPersonValidation(teacher) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = teacherService.addTeacher(teacher);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/teacher/search";
	}

	@RequestMapping("/delete")
	public String deleteTeacher(int id, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkTeacherIdValidation(id) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = teacherService.deleteTeacher(id);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/teacher/search";
	}

	@RequestMapping("/modify")
	public String modifyTeacher(Teacher teacher, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkPersonValidation(teacher) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = teacherService.modifyTeacher(teacher);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/teacher/search";
	}

	@RequestMapping("/search")
	public String searchTeachers(Model model) {
		List<Teacher> teachers = teacherService.searchTeachers();
		model.addAttribute("teachers", teachers);
		return PersonViewPath.TEACHER_PAGE.getPath();
	}

	@RequestMapping("/search/specific")
	public String searchSpecificTeachers(String sel_opt, String search_value, Model model,
			RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSearchSpecificInputValidation(sel_opt, search_value) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/student/search";
		}

		List<Teacher> teachers = teacherService.searchSpecificTeachers(sel_opt, search_value);
		model.addAttribute("teachers", teachers);
		return PersonViewPath.TEACHER_PAGE.getPath();
	}
}
