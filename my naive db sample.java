import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class SQLInjectionExample extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db");
            String user = request.getParameter("username");

            String query = "SELECT * FROM users WHERE username = ?;";
            PreparedStatement stmt = con.prepareStatement(query);

            stmt.setString(1, user);
            stmt.executeQuery();

            
            String query2 = "SELECT * FROM users WHERE username = ?;";
            PreparedStatement stmt2 = con.prepareStatement(query2);

            stmt2.setString(1, user);
            stmt2.executeQuery();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
