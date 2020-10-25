package ch.rhjoerg.maven.repository.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MavenRepositoryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private String service;

	@Override
	public void init() throws ServletException
	{
		service = getServletConfig().getInitParameter("service");
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getOutputStream().print(service);
	}
}
