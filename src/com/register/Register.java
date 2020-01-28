package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.ConnectionProvider;

public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String job=request.getParameter("job");
		String email=request.getParameter("email");
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
		Connection connection=ConnectionProvider.getConn();
		
		
		try {
			PreparedStatement ps=connection.prepareStatement("select * from labourhub where email=?");
			ps.setString(1, email);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				
				out.println("<font color='red'><b>This Email Already Registered</b></font>");
				RequestDispatcher rd=request.getRequestDispatcher("resistration.html");
				rd.include(request, response);
			}
			else
			{	

				try {
					
					
					PreparedStatement ps1=connection.prepareStatement("insert into labourhub values(?,?,?,?)");
					ps1.setString(1, name);
					ps1.setString(2, email);
					ps1.setString(3, pass);
					ps1.setString(4, job);
					ps1.executeUpdate();
					RequestDispatcher rd=request.getRequestDispatcher("login.html");
					rd.forward(request, response);
					}
				
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		
		
		
		
		
		
	}

	private PreparedStatement PreparedStatement(String string) {
		// TODO Auto-generated method stub
		return null;
	}

}
