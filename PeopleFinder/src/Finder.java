import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Servlet implementation class Finder
 */
@WebServlet("/Finder")
public class Finder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Finder() {
        super();
        message = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		message = "";
		response.setContentType("text/html");
		String name = request.getParameter("lastName");
		message = findName(name);
		if (message.equals("No matched names.")) {
			message = "<div class=\"container\"><div class=\"jumbotron\"><h4>" + message + "</h4></div></div>";
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/Search.jsp").forward(request, response);
		} else {
			request.setAttribute("message", message);
			getServletContext().getRequestDispatcher("/Output.jsp").forward(request, response);
		}
	}

	private static String findName(String name) {
		String content = "";
		try {
			//URL of Oracle database server
			String url = "jdbc:oracle:thin:testuser/password@localhost";

			//properties for creating connection to Oracle database
			Properties props = new Properties();
			props.setProperty("user", "testdb");
			props.setProperty("password", "password");

			Class.forName("oracle.jdbc.driver.OracleDriver");
			//creating connection to Oracle database using JDBC
			Connection conn = DriverManager.getConnection(url, props);
			
			String sql = "SELECT * FROM ("
						+ "SELECT customers.title, customers.firstname, customers.lastname AS \"lastname\", customers.streetaddress, cities.name AS \"city\", states.name AS \"state\", customers.zipcode, customers.emailaddress, customers.position, companies.name AS \"company\" "
						+ "FROM customers "
						+ "INNER JOIN cities ON customers.city = cities.id "
						+ "INNER JOIN states ON customers.state = states.id "
						+ "INNER JOIN companies ON customers.companyid = companies.companyid) "
						+ "WHERE \"lastname\" LIKE '" + name + "%'";
			
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			
			if (result.next()) {
				content = "<div class=\"container\"><h3>Results</h3><table class=\"table table-striped\"><thead><tr><th>Title</th><th>First Name</th><th>Last Name</th><th>Street Address</th><th>City</th><th>State</th><th>Zip Code</th><th>Email Address</th><th>Position</th><th>Company</th></tr></thead><tbody>";
				while (result.next()) {
					content += "<tr><td>" + result.getString("title") + "</td><td>" + result.getString("firstname") + "</td><td>" + result.getString("lastname") + "</td><td>" + result.getString("streetaddress") + "</td><td>" + result.getString("city") + "</td><td>" + result.getString("state") + "</td><td>" + result.getString("zipcode").substring(0, 5) + "</td><td>" + result.getString("emailaddress") + "</td><td>" + result.getString("position") + "</td><td>" + result.getString("company") + "</td></tr>";
				}
				content += "</tbody></table></div>";
			} else {
				content = "No matched names.";
			}
			
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();;
		}
		return content;
	}
	
}
