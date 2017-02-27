package com.nts.school.servlet.subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.SubjectService;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.object.Subject;

/**
 * Servlet implementation class SubjectServlet
 */
@WebServlet("/SearchSubjectServlet")
public class SearchSubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectService subjectService;

	public SearchSubjectServlet() {
		super();
		subjectService = new SubjectService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<Subject> subjects = new ArrayList<Subject>();
			subjects = subjectService.searchSubject();

			request.setAttribute("subjects", subjects);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.SUBJECT_PAGE.getPath());
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
