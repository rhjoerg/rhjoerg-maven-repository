package ch.rhjoerg.maven.repository.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ch.rhjoerg.maven.repository.context.MavenRepositoryContext;

public class MavenRepositoryContextListener implements ServletContextListener
{
	private MavenRepositoryContext createMavenRepositoryContext(ServletContext servletContext) throws Exception
	{
		String typeName = servletContext.getInitParameter(MavenRepositoryContext.class.getName());
		Class<?> type = Class.forName(typeName);
		Object instance = type.getDeclaredConstructor().newInstance();

		return MavenRepositoryContext.class.cast(instance);
	}

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		ServletContext servletContext = sce.getServletContext();

		try
		{
			MavenRepositoryContext repositoryContext = createMavenRepositoryContext(servletContext);

			servletContext.setAttribute(MavenRepositoryContext.class.getName(), repositoryContext);
			servletContext.log("MavenRepositoryContextListener.contextInitialized");
		}
		catch (Exception e)
		{
			servletContext.log(e.getMessage(), e);
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
	}
}
