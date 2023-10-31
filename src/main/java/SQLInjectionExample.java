import java.sql.*;

public class SQLInjectionExample {
    public static void main(String[] args) throws SQLException {
        String userInputA = args[1];

        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db");
        

        String query = "SELECT * FROM users WHERE username = '?';";
        PreparedStatement stmt = con.prepareStatement(query);
        try {
            stmt.setInt(1, Math.round(Float.parseFloat(userInputA)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        ResultSet rs = stmt.executeQuery();
    }
}
