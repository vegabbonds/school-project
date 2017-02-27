package com.nts.intern.school.subject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nts.intern.school.subject.path.SubjectViewPath;
import com.nts.intern.school.subject.service.SubjectService;
import com.nts.intern.school.subject.vo.Subject;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.validation.ValidateUtil;

@Controller
@RequestMapping("/subject")
public class SubjectController {
	@Autowired
	SubjectService subjectService;

	@Autowired
	ValidateUtil validateUtil;

	@RequestMapping("/add")
	public String addSubject(Subject subject, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkSubjectValidation(subject) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = subjectService.addSubject(subject);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/subject/search";
	}

	@RequestMapping("/delete")
	public String deleteSubject(int id, RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSubjectIdValidation(id) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/subject/search";
		}

		try {
			ResultMessage resultMessage = subjectService.deleteSubject(id);
			redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		} catch (Exception e) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.DELETE_FAIL.getMessage());
		}

		return "redirect:/subject/search";
	}

	@RequestMapping("/modify")
	public String modifySubject(Subject subject, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkSubjectValidation(subject) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = subjectService.modifySubject(subject);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/subject/search";
	}

	@RequestMapping("/search")
	public String searchSubjects(Model model) {
		List<Subject> subjects = subjectService.searchSubjects();
		model.addAttribute("subjects", subjects);
		return SubjectViewPath.SUBJECT_PAGE.getPath();
	}

	@RequestMapping("/search/specific")
	public String searchSpecificSubjects(String sel_opt, String search_value, Model model,
			RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSearchSpecificInputValidation(sel_opt, search_value) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/subject/search";
		}

		List<Subject> subjects = subjectService.searchSpecificSubjects(sel_opt, search_value);
		model.addAttribute("subjects", subjects);
		return SubjectViewPath.SUBJECT_PAGE.getPath();
	}
}
