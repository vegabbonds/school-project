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
import com.nts.school.vo.viewobject.StudentScore;

/**
 * Servlet implementation class ScoreServlet
 */
/**
 * 모든 성적정보를 조회할 때 사용하는 servlet
 * @author 이정석
 */
@WebServlet("/SearchScoreServlet")
public class SearchScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ScoreService scoreService;

	public SearchScoreServlet() {
		super();
		scoreService = new ScoreService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<StudentScore> searchScoreList = new ArrayList<StudentScore>();
			searchScoreList = scoreService.searchScore();

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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
