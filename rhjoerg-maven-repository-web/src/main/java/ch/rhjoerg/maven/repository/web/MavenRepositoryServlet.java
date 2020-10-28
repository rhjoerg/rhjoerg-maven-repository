package ch.rhjoerg.maven.repository.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.rhjoerg.maven.repository.spi.MavenRepositoryContext;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryService;

public class MavenRepositoryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private MavenRepositoryContext getContext()
	{
		return MavenRepositoryContext.class.cast(getServletContext().getAttribute(MavenRepositoryContext.class.getName()));
	}

	@SuppressWarnings("unused")
	private MavenRepositoryService getService()
	{
		return getContext().getService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getOutputStream().print(request.getPathInfo());
	}
}
