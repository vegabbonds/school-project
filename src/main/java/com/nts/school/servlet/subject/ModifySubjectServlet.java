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
import com.nts.school.service.ValidateService;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.exception.InputFormatException;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.object.Subject;

/**
 * Servlet implementation class ModifySubjectServlet
 */
@WebServlet("/ModifySubjectServlet")
public class ModifySubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectService subjectService;
	private ValidateService validateService;

	public ModifySubjectServlet() {
		super();
		subjectService = new SubjectService();
		validateService = new ValidateService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Subject> subjects = new ArrayList<Subject>();
		try {
			String getId = request.getParameter("modify_id");
			int id = Integer.parseInt(getId);
			String name = request.getParameter("name");
			Subject subject = new Subject(id, name);

			if (validateService.checkSubjectValidation(subject) != true) {
				throw new InputFormatException("InputFormat Exception");
			}
			ResultMessage resultMessage = subjectService.modifySubject(subject);
			subjects = subjectService.searchSubject();

			request.setAttribute("message", resultMessage.getMessage());
			request.setAttribute("subjects", subjects);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.SUBJECT_PAGE.getPath());
			dispatcher.forward(request, response);

		} catch (InputFormatException e) {
			subjects = subjectService.searchSubject();

			request.setAttribute("message", e.getMessage());
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
