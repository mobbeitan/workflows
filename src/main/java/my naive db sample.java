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

            String query = "SELECT * FROM users WHERE username = '?';";
            PreparedStatement stmt = con.prepareStatement(query);

            try {
                stmt.setInt(1, Math.round(Float.parseFloat(user)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            stmt.executeQuery();
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
