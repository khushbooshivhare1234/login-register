import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String username = request.getParameter("uname");
        String password = request.getParameter("pass");
		  if (isValidLogin(username, password)) {
			  RequestDispatcher rd=request.getRequestDispatcher("servlet2");  
		        rd.forward(request, response);  
	        } else {
	        	   pw.print("Sorry UserName or Password Error!");  
	               RequestDispatcher rd=request.getRequestDispatcher("/index.html");  
	               rd.include(request, response); 
	        }
	}
	 private boolean isValidLogin(String username, String password) {
	        String jdbcUrl = "jdbc:mysql://localhost:3306/usermanagement";
	        String jdbcUser = "root";
	        String jdbcPassword = "Khushboo@1234";

	        try (Connection connection = DriverManager.getConnection(jdbcUrl, jdbcUser, jdbcPassword)) {
	            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	                preparedStatement.setString(1, username);
	                preparedStatement.setString(2, password);
	                ResultSet resultSet = preparedStatement.executeQuery();
	                return resultSet.next(); // If the query returns a row, the login is valid
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
