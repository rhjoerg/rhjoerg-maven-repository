package ch.rhjoerg.maven.repository.standalone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import ch.rhjoerg.maven.repository.context.loader.ServiceLoaderMavenRepositoryContext;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryContext;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryProperties;
import ch.rhjoerg.maven.repository.web.MavenRepositoryContextListener;
import ch.rhjoerg.maven.repository.web.MavenRepositoryServlet;

public class MavenRepositoryServer
{
	private static Server createServer()
	{
		Server server = new Server(MavenRepositoryProperties.getPort());
		ServletContextHandler context = new ServletContextHandler();

		context.setContextPath("/");
		context.setInitParameter(MavenRepositoryContext.class.getName(), ServiceLoaderMavenRepositoryContext.class.getName());
		context.addServlet(MavenRepositoryServlet.class, "/*");
		context.getServletHandler().addListener(new ListenerHolder(MavenRepositoryContextListener.class));

		server.setHandler(context);

		return server;
	}

	public static void main(String[] args) throws Exception
	{
		Server server = createServer();

		server.start();
		System.out.println(server.getURI());
		server.join();
	}
}
