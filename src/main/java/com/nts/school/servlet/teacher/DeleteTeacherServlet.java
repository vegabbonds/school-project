package com.nts.school.servlet.teacher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.TeacherService;
import com.nts.school.service.ValidateService;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.exception.InputFormatException;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.person.Teacher;

/**
 * Servlet implementation class DeleteTeacherServlet
 */
@WebServlet("/DeleteTeacherServlet")
public class DeleteTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService;
	private ValidateService validateService;

	public DeleteTeacherServlet() {
		super();
		teacherService = new TeacherService();
		validateService = new ValidateService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Teacher> teachers = new ArrayList<Teacher>();
		try {
			String getId = request.getParameter("id");
			int id = Integer.parseInt(getId);

			if (validateService.checkTeacherIdValidation(id) != true) {
				throw new InputFormatException("InputFormat Exception");
			}
			ResultMessage resultMessage = teacherService.deleteTeacher(id);
			teachers = teacherService.searchTeacher();

			request.setAttribute("message", resultMessage.getMessage());
			request.setAttribute("teachers", teachers);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.TEACHER_PAGE.getPath());
			dispatcher.forward(request, response);

		} catch (InputFormatException e) {
			teachers = teacherService.searchTeacher();

			request.setAttribute("message", e.getMessage());
			request.setAttribute("teachers", teachers);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.TEACHER_PAGE.getPath());
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
