package com.nts.school.servlet.score;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.ScoreService;
import com.nts.school.service.ValidateService;
import com.nts.school.util.exception.InputFormatException;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.viewobject.StudentScore;

/**
 * Servlet implementation class SearchSpecificScoreServlet
 */
/**
 * 특정 학생의 모든 성적정보를 조회하고자 할때 호출하는 servlet.
 * @author 이정석
 */
@WebServlet("/SearchSpecificScoreServlet")
public class SearchSpecificScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;
	private ValidateService validateService;

	public SearchSpecificScoreServlet() {
		super();
		scoreService = new ScoreService();
		validateService = new ValidateService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<StudentScore> searchScoreList = new ArrayList<StudentScore>();
		try {
			String getId = request.getParameter("specific_id");
			int specificStudentId = Integer.parseInt(getId);

			if (validateService.checkStudentIdValidation(specificStudentId) != true) {
				throw new InputFormatException("InputFormat Exception");
			}
			searchScoreList = scoreService.searchSpecificScore(specificStudentId);

			request.setAttribute("scores", searchScoreList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.SCORE_PAGE.getPath());
			dispatcher.forward(request, response);

		} catch (InputFormatException e) {
			searchScoreList = scoreService.searchScore();

			request.setAttribute("message", e.getMessage());
			request.setAttribute("scores", searchScoreList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.SCORE_PAGE.getPath());
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.ERROR_PAGE.getPath());
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		doGet(request, response);
	}

}
