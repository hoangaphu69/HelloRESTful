package hoangaphu.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet(description = "this a demo servlet", urlPatterns = { "/hello" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
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
		loadParameter(request, response, "by get");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		loadParameter(request, response, "by post");
		
	}
	public void loadParameter(HttpServletRequest request, 
			HttpServletResponse response, String method) throws IOException, ServletException{
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		PrintWriter printWriter = response.getWriter();
		printWriter.println("helloWorld "+ method +"<br>");
		printWriter.println("this is a demo sample <br>");
		
		printWriter.println("username: "+username+"<br>");
		printWriter.println("password: "+password+"<br>");
	}
}
