package com.nts.intern.school.person.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nts.intern.school.person.path.PersonViewPath;
import com.nts.intern.school.person.service.StudentService;
import com.nts.intern.school.person.vo.Student;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.validation.ValidateUtil;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	StudentService studentService;

	@Autowired
	ValidateUtil validateUtil;

	@RequestMapping("/add")
	public String addStudent(Student student, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkPersonValidation(student) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = studentService.addStudent(student);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/student/search";
	}

	@RequestMapping("/delete")
	public String deleteStudent(int id, RedirectAttributes redirectAttributes) {
		if (validateUtil.checkStudentIdValidation(id) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/student/search";
		}

		try {
			ResultMessage resultMessage = studentService.deleteStudent(id);
			redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.DELETE_FAIL.getMessage());
		}
		return "redirect:/student/search";
	}

	@RequestMapping("/modify")
	public String modifyStudent(Student student, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkPersonValidation(student) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = studentService.modifyStudent(student);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/student/search";
	}

	@RequestMapping("/search")
	public String searchStudents(Model redirectAttributes) {
		List<Student> students = studentService.searchStudents();
		redirectAttributes.addAttribute("students", students);
		return PersonViewPath.STUDENT_PAGE.getPath();
	}

	@RequestMapping("/search/specific")
	public String searchSpecificStudents(String sel_opt, String search_value, Model model,
			RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSearchSpecificInputValidation(sel_opt, search_value) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/student/search";
		}

		List<Student> students = studentService.searchSpecificStudents(sel_opt, search_value);
		model.addAttribute("students", students);
		return PersonViewPath.STUDENT_PAGE.getPath();
	}
}
