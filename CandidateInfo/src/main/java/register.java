

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	    maxFileSize = 1024 * 1024 * 10,      // 10MB
	    maxRequestSize = 1024 * 1024 * 50    // 50MB
	)
@WebServlet("/register")
public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		 response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        
	        // Get form parameters
	        String name = request.getParameter("name");
	        String dob = request.getParameter("dob");
	        String gender = request.getParameter("gender");
	        String fatherName = request.getParameter("fathername");
	        String motherName = request.getParameter("mothername");
	        String location = request.getParameter("location");
	        String email = request.getParameter("email");
	        String phone = request.getParameter("phone");
	        String qualification = request.getParameter("qualification");
	        String college = request.getParameter("college");
	        String passoutYear = request.getParameter("passout");
	        
	        Connection conn = null;
	        PreparedStatement pst = null;
	        
	        try {
	            // Load JDBC driver
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            
	            // Connect to database
	            conn = DriverManager.getConnection("jdbc:mysql://gondola.proxy.rlwy.net:53305/railway", "root", "kuzIykvbMXjlMbQfdeaiLkGpAcNwvTVU");
	            
	            // Insert query
	            String query = "INSERT INTO candidates (name, dob, gender, father_name, mother_name, location, email, phone, qualification, college, passout_year) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            pst = conn.prepareStatement(query);
	            
	            pst.setString(1, name);
	            pst.setString(2, dob);
	            pst.setString(3, gender);
	            pst.setString(4, fatherName);
	            pst.setString(5, motherName);
	            pst.setString(6, location);
	            pst.setString(7, email);
	            pst.setString(8, phone);
	            pst.setString(9, qualification);
	            pst.setString(10, college);
	            pst.setString(11, passoutYear);
	            
	            int result = pst.executeUpdate();
	            
	            if (result > 0) {
	                out.println("<script>alert('Registration Successful!, Details submitted to Ashok Successfully!'); window.location='index.html';</script>");
	            } else {
	                out.println("<script>alert('Registration Failed!'); window.location='index.html';</script>");
	            }
	            
	        } catch (Exception e) {
	            out.println("Error: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            try {
	                if (pst != null) pst.close();
	                if (conn != null) conn.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}

 