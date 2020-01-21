<%@ page language="java" import="java.sql.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorhandler.jsp"%>

<%-- errorPage Set to "errorhandler.jsp" --%>

<%!


Connection con;
PreparedStatement ps;

//override jspInit() Method to Open Connection
public void jspInit()
{
try{  
		Class.forName("com.mysql.jdbc.Driver");  
		con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/dao","root","root");  
		ps=con.prepareStatement("insert into student value(?,?,?)");
	
}catch(Exception e){ System.out.println(e);}  
}
//override jspDestroy() Method to close Connection

public void jspDestroy()
{
	try{
		ps.close();
	    con.close();
	}
	catch(Exception e)
	{
		 System.out.println(e);
	}
}
%>

<%
int id=Integer.parseInt(request.getParameter("sno"));
String name=request.getParameter("sname");
int per=Integer.parseInt(request.getParameter("sperc"));
ps.setInt(1, id);
ps.setString(2,name);
ps.setInt(3,per);


ps.execute();





%>