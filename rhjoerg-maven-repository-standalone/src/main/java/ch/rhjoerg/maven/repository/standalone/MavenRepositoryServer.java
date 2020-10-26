package ch.rhjoerg.maven.repository.standalone;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ListenerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import ch.rhjoerg.maven.repository.context.loader.ServiceLoaderMavenRepositoryContext;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryContext;
import ch.rhjoerg.maven.repository.web.MavenRepositoryContextListener;
import ch.rhjoerg.maven.repository.web.MavenRepositoryServlet;

public class MavenRepositoryServer
{
	public static final Integer DEFAULT_PORT = 8000;

	public static CommandLine parseCommandLine(String[] args) throws ParseException
	{
		Options options = new Options();
		CommandLineParser parser = new DefaultParser();

		options.addOption("p", "port", true, "port");

		return parser.parse(options, args);
	}

	public static Server createServer(int port)
	{
		Server server = new Server(port);
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
		CommandLine cmd = parseCommandLine(args);
		int port = Integer.parseInt(cmd.getOptionValue("port", DEFAULT_PORT.toString()));

		Server server = createServer(port);

		server.start();
		System.out.println("http://localhost:" + port);
		server.join();
	}
}
