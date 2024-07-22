package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Management.EmpService;


public class Empserverlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	private EmpService empService;

   
    public Empserverlet() {
      this.empService=empService;
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("work", this.empService.getEmp());
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("/WEB-INF/work.jsp");
		requestDispatcher.forward(request,response);
	
	
	}

}
