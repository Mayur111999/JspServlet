package com.connection;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/JndiLookupExample")
public class JndiLookupExample extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Context ctx = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:/comp/env/mydb");
			
			con = ds.getConnection();
			stmt = con.createStatement();
			
			rs = stmt.executeQuery("select Sid, Sname from student");
			
			PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.print("<html><body><h2>Student Details</h2>");
            out.print("<table border=\"1\" cellspacing=10 cellpadding=5>");
            out.print("<th>StudentID</th>");
            out.print("<th>Student Name</th>");
            
            while(rs.next())
            {
                out.print("<tr>");
                out.print("<td>" + rs.getInt("Sid") + "</td>");
                out.print("<td>" + rs.getString("Sname") + "</td>");
                out.print("</tr>");
            }
            out.print("</table></body><br/>");
          
            out.print("</html>");
            
		}catch(NamingException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				stmt.close();
				con.close();
				ctx.close();
			} catch (SQLException e) {
				System.out.println("Exception in closing DB resources");
			} catch (NamingException e) {
				System.out.println("Exception in closing Context");
			}
			
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}


/*
 *----------------------------------------------------------------------------------------------
 OUTPUT ON http://localhost:8080/WJndiLookup/JndiLookupExample  : -

 -------------------------------------------------------------------------------------------------
 Student Details
StudentID	Student Name
102	        Ram
105	        Suraj
110	        Sayali

-------------------------------------------------------------------------------------------------
Context.xml change
-------------------------------------------------------------------------------------------------

<ResourceLink name="mydb"
                global="mydb"
                auth="Container"
                type="javax.sql.DataSource" />
-------------------------------------------------------------------------------------------------

server.xml change
-------------------------------------------------------------------------------------------------
 <Resource 
 auth="Container" 
 driverClassName="com.mysql.jdbc.Driver" 
 global="mydb" 
 maxActive="100" 
 maxIdle="20" 
 maxWait="10000" 
 minIdle="5" 
 name="mydb" 
 password="root" 
 type="javax.sql.DataSource"
 url="jdbc:mysql://localhost:3306/dao" 
 username="root"/>
    







 */
