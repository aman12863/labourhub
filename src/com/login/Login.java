package com.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionProvider;


public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		String job=request.getParameter("job");
		
		
		Connection connection=ConnectionProvider.getConn();
		try {
			PreparedStatement ps=connection.prepareStatement("select * from labourhub where email=? && pass=? && job=?");
			ps.setString(1, email);
			ps.setString(2, pass);
			ps.setString(3, job);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				String a=rs.getString(4);
				String ab="Job";
				if(a.equals(ab))
				{
				response.sendRedirect("job.html");
				}
				else
				{
					response.sendRedirect("hire.html");
				}
			}
			else
			{	
				out.println("<font color='red'><b>You have entered incorrect Information</b></font>");
				RequestDispatcher rd = request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
			
			
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
	}

}
