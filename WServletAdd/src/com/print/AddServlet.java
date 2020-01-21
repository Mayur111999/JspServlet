package com.print;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class AddServlet extends GenericServlet {

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {

		if(req.getParameter("n1")!=null&&req.getParameter("n2")!=null)
		{
		String num1 = req.getParameter("n1");
		String num2 = req.getParameter("n2");
		int n1=Integer.parseInt(num1);
		int n2=Integer.parseInt(num2);
		int y=n1+n2;
		PrintWriter out = res.getWriter();
		out.println("Result is : - "+y);
		
		}
		
	}

}
