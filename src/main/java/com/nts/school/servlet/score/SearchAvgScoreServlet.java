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
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.viewobject.StudentAvg;

/**
 * Servlet implementation class SearchAvgScoreServlet
 */
/**
 * 모든 학생들의 평균 정보를 조회할 때 사용하는 servlet
 * @author 이정석
 */
@WebServlet("/SearchAvgScoreServlet")
public class SearchAvgScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;

	public SearchAvgScoreServlet() {
		super();
		scoreService = new ScoreService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<StudentAvg> searchAvgList = new ArrayList<StudentAvg>();
			searchAvgList = scoreService.searchAvgScore();

			request.setAttribute("avgScores", searchAvgList);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.AVG_SCORE_PAGE.getPath());
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
