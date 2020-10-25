package ch.rhjoerg.maven.repository.web;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MavenRepositoryContextListener implements ServletContextListener
{

	@Override
	public void contextInitialized(ServletContextEvent sce)
	{
		sce.getServletContext().log("MavenRepositoryContextListener.contextInitialized");

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce)
	{
	}
}
