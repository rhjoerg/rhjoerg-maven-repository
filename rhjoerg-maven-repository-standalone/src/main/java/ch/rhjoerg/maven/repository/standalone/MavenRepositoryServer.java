package ch.rhjoerg.maven.repository.standalone;

import org.eclipse.jetty.server.Server;

public class MavenRepositoryServer
{
	public static void main(String[] args)
	{
		Server server = new Server(6000);

		try
		{
			server.start();
			server.join();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
