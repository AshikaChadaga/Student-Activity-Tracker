import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/alog")
public class alog extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{  
	
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		String usn=request.getParameter("id");  
		String entered=request.getParameter("psw");
		       
		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/activity","root","");
			PreparedStatement st=con.prepareStatement("select * from alogin where id = ?");
			st.setString(1,usn);  
			
			ResultSet rs = st.executeQuery();
			rs.next();
			String pswd = rs.getString(2);
			
			if(entered.equals(pswd))
			{
			 response.sendRedirect("view.html");
			}
			else
				out.println("Invalid login");
			
			       
		}
		catch (Exception e2)
		{
		System.out.println(e2);
		}  
		
	}

}