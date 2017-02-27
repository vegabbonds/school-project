package com.nts.school.servlet.score;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.ScoreService;
import com.nts.school.util.path.ViewPath;

/**
 * Servlet implementation class SearchOverallAvgScoreServlet
 */
/**
 * 전체 학생들의 평균에 대한 평균을 구할 때 호출하는 servlet
 * @author 이정석
 */
@WebServlet("/SearchOverallAvgScoreServlet")
public class SearchOverallAvgScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchOverallAvgScoreServlet() {
		super();
		scoreService = new ScoreService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			double overallAvgScore = scoreService.searchOvearallAvgScore();

			request.setAttribute("overallAvgScore", overallAvgScore);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.OVERALL_AVG_SCORE_PAGE.getPath());
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
