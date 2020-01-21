package com.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/ConnectionPoolingServlet")
public class ConnectionPoolingServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Context con1 = new InitialContext();
			DataSource ds=(DataSource) con1.lookup("java:comp/env/mydb");
			Connection c1 = ds.getConnection();
			System.out.println(c1);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}

/*
 --------------------------------------------------------------------------------------------------------
  OUTPUT: - 
  1595531975, URL=jdbc:mysql://localhost:3306/dao, UserName=root@localhost, MySQL Connector Java
 
 -------------------------------------------------------------------------------------------------------
    Context.xml changes: - 
    
    <Resource name="mydb" 
     
      auth="Container" 
      type="javax.sql.DataSource" 
      driverClassName="com.mysql.jdbc.Driver" 
      url="jdbc:mysql://localhost:3306/dao" 
      username="root" 
      password="root" 
     
    />
    
 */
