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
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.person.Teacher;

/**
 * Servlet implementation class TeacherServlet
 */
@WebServlet("/SearchTeacherServlet")
public class SearchTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherService teacherService;

	public SearchTeacherServlet() {
		super();
		teacherService = new TeacherService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		try {
			List<Teacher> teachers = new ArrayList<Teacher>();
			teachers = teacherService.searchTeacher();

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
