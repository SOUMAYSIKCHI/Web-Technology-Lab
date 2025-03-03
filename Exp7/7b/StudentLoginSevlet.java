package pres;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class StudentLoginServlet extends HttpServlet {     
    private Connection con = null;     

    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Corrected driver
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mrecw", "root", "naresh");
            System.out.println("Got database connection");
        } catch (Exception e) {
            System.out.println("Not able to establish database connection");
            e.printStackTrace();
        }
    }

    public void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM login WHERE uname=? AND pwd=?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, uname);
            pstmt.setString(2, pwd);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
                rd.forward(req, resp);
            } else {
                RequestDispatcher rd = req.getRequestDispatcher("login.html");
                rd.include(req, resp);
                out.println("<h3 align='center' style='color:red;'>Wrong credentials</h3>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error during login process");
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void destroy() {
        try {
            if (con != null) con.close();
            System.out.println("Database connection closed");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
