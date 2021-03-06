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
import com.nts.school.util.ResultMessage;
import com.nts.school.util.exception.InputFormatException;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.object.Score;
import com.nts.school.vo.viewobject.StudentScore;

/**
 * 요청한 작업을 적절한 service를 호출하고,
 * 적절한 jsp를 사용자에게 보여주도록 한다.
 * Servlet implementation class AddScoreServlet
 * @author 이정석
 */
@WebServlet("/AddScoreServlet")
public class AddScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;
	private ValidateService validateService;

	public AddScoreServlet() {
		super();
		scoreService = new ScoreService();
		validateService = new ValidateService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<StudentScore> searchScoreList = new ArrayList<StudentScore>();
		try {
			String getId = request.getParameter("id");
			int id = Integer.parseInt(getId);
			String getSubjectId = request.getParameter("subjectId");
			int subjectId = Integer.parseInt(getSubjectId);
			String getScore = request.getParameter("score");
			int subjectScore = Integer.parseInt(getScore);
			Score score = new Score(id, subjectId, subjectScore);

			if (validateService.checkScoreValidation(score) != true) {
				throw new InputFormatException("InputFormat Exception");
			}
			ResultMessage resultMessage = scoreService.addScore(score);
			searchScoreList = scoreService.searchScore();

			request.setAttribute("message", resultMessage.getMessage());
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
