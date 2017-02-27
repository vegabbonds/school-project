package com.nts.school.servlet.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nts.school.service.StaffService;
import com.nts.school.service.ValidateService;
import com.nts.school.util.path.ViewPath;
import com.nts.school.vo.person.Staff;

/**
 * Servlet implementation class SearchSpecificStaffServlet
 */
@WebServlet("/SearchSpecificStaffServlet")
public class SearchSpecificStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffService staffService;
	private ValidateService validateService;

	public SearchSpecificStaffServlet() {
		super();
		staffService = new StaffService();
		validateService = new ValidateService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		List<Staff> staffs = new ArrayList<Staff>();
		try {
			String selectOption = request.getParameter("sel_opt");
			String id = request.getParameter("specific_id");

			staffs = staffService.searchSpecificStaff(id, selectOption);

			request.setAttribute("staffs", staffs);
			RequestDispatcher dispatcher = request.getRequestDispatcher(ViewPath.STAFF_PAGE.getPath());
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
