package ch.rhjoerg.maven.repository.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.rhjoerg.maven.repository.context.MavenRepositoryContext;

public class MavenRepositoryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private MavenRepositoryContext getMavenRepositoryContext()
	{
		return MavenRepositoryContext.class.cast(getServletContext().getAttribute(MavenRepositoryContext.class.getName()));
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getOutputStream().print(getMavenRepositoryContext().toString());
	}
}
