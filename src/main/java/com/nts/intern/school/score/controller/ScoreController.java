package com.nts.intern.school.score.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nts.intern.school.score.path.ScoreViewPath;
import com.nts.intern.school.score.service.ScoreService;
import com.nts.intern.school.score.vo.Score;
import com.nts.intern.school.score.vo.StudentAvgScore;
import com.nts.intern.school.score.vo.StudentScore;
import com.nts.intern.school.util.message.ResultMessage;
import com.nts.intern.school.util.validation.ValidateUtil;

@Controller
@RequestMapping("/score")
public class ScoreController {
	@Autowired
	ScoreService scoreService;

	@Autowired
	ValidateUtil validateUtil;

	@RequestMapping("/add")
	public String addScore(Score score, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkScoreValidation(score) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = scoreService.addScore(score);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/score/search";
	}

	@RequestMapping("/delete")
	public String deleteScore(Score score, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkScoreValidation(score) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = scoreService.deleteScore(score);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/score/search";
	}

	@RequestMapping("/modify")
	public String modifyScore(Score score, RedirectAttributes redirectAttributes) {
		ResultMessage resultMessage;

		if (validateUtil.checkScoreValidation(score) == false) {
			resultMessage = ResultMessage.INPUT_FORMAT_ERROR;
		} else {
			resultMessage = scoreService.modifyScore(score);
		}

		redirectAttributes.addFlashAttribute("message", resultMessage.getMessage());
		return "redirect:/score/search";
	}

	@RequestMapping("/search")
	public String searchScores(Model model) {
		List<StudentScore> scores = scoreService.searchScores();
		model.addAttribute("scores", scores);
		return ScoreViewPath.SCORE_PAGE.getPath();
	}

	@RequestMapping("/search/specific")
	public String searchSpecificScores(int search_value, Model model, RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSearchSpecificInputValidation(search_value) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/score/search";
		}

		List<StudentScore> scores = scoreService.searchSpecificScores(search_value);
		model.addAttribute("scores", scores);
		return ScoreViewPath.SCORE_PAGE.getPath();
	}

	@RequestMapping("/search/avg")
	public String searchAvgScores(Model model) {
		List<StudentAvgScore> avgScores = scoreService.searchAvgScores();
		model.addAttribute("avgScores", avgScores);
		return ScoreViewPath.SCORE_AVG_PAGE.getPath();
	}

	@RequestMapping("/search/avg/specific")
	public String searchAvgScoresByStudentId(int search_value, Model model, RedirectAttributes redirectAttributes) {
		if (validateUtil.checkSearchSpecificInputValidation(search_value) == false) {
			redirectAttributes.addFlashAttribute("message", ResultMessage.INPUT_FORMAT_ERROR.getMessage());
			return "redirect:/score/search";
		}

		List<StudentAvgScore> avgScores = scoreService.searchAvgScoresByStudentId(search_value);
		model.addAttribute("avgScores", avgScores);
		return ScoreViewPath.SCORE_AVG_PAGE.getPath();
	}

	@RequestMapping("/search/overall/avg")
	public String searchOverallAvgScore(Model model) {
		double overallAvgScore = scoreService.searchOverallAvgScore();
		model.addAttribute("overallAvgScore", overallAvgScore);
		return ScoreViewPath.SCORE_OVERALL_AVG_PAGE.getPath();
	}
}
