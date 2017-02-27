package com.nts.school.servlet.student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.StudentService;
import com.nts.school.service.ValidateService;
import com.nts.school.util.ResultMessage;
import com.nts.school.util.exception.InputFormatException;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.person.Student;

/**
 * Servlet implementation class AddStudentServlet
 */
@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService;
	private ValidateService validateService;

	public AddStudentServlet() {
		super();
		studentService = new StudentService();
		validateService = new ValidateService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Student> students = new ArrayList<Student>();
		try {
			String getId = request.getParameter("id");
			int id = Integer.parseInt(getId);
			String name = request.getParameter("name");
			String birthDate = request.getParameter("birthDate");
			Student student = new Student(id, name, birthDate);

			if (validateService.checkPersonValidation(student) != true) {
				throw new InputFormatException("InputFormat Exception");
			}
			ResultMessage resultMessage = studentService.addStudent(student);
			students = studentService.searchStudent();

			request.setAttribute("message", resultMessage.getMessage());
			request.setAttribute("students", students);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.STUDENT_PAGE.getPath());
			dispatcher.forward(request, response);

		} catch (InputFormatException e) {
			students = studentService.searchStudent();

			request.setAttribute("message", e.getMessage());
			request.setAttribute("students", students);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.STUDENT_PAGE.getPath());
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
