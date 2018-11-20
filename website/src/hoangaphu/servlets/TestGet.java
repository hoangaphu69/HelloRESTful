package hoangaphu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestGet
 */
@WebServlet(description = "this is a demo get",urlPatterns = "/testget")
public class TestGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestGet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		PrintWriter writer = response.getWriter();
		
		writer.println("this is testget <br>");
		writer.println("userName: "+userName+"<br>");
		writer.println("password: "+password+"<br>");
		
	}

}
