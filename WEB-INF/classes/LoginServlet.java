import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    private Connection con;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Sathwik123");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String username = request.getParameter("uname");
        String password = request.getParameter("pwd");
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from login where uname='" + username + "' and pwd='" + password + "' ");
            PrintWriter out = response.getWriter();
            if (rs.next()) {
                out.println("Welcome to: " + username);
            } else {
                out.println("Wrong credentials");
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
