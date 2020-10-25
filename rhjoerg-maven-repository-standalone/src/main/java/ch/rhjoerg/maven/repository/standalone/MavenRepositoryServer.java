package ch.rhjoerg.maven.repository.standalone;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import ch.rhjoerg.maven.repository.web.MavenRepositoryContextListener;
import ch.rhjoerg.maven.repository.web.MavenRepositoryServlet;

public class MavenRepositoryServer
{
	public static Server createServer(int port)
	{
		Server server = new Server(port);
		ServletContextHandler context = new ServletContextHandler();

		context.setContextPath("/");
		context.addServlet(MavenRepositoryServlet.class, "/*");
		context.getServletHandler().addListener(new ListenerHolder(MavenRepositoryContextListener.class));

		server.setHandler(context);

		return server;
	}

	public static void main(String[] args) throws Exception
	{
		Server server = createServer(8000);

		server.start();
		server.join();
	}
}
