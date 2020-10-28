package ch.rhjoerg.maven.repository.spi;

public interface MavenRepositoryProperties
{
	public static final String PORT_KEY = "rhjoerg-maven-repository.port";
	public static final String PORT_VALUE = "80";

	public static final String DATA_KEY = "rhjoerg-maven-repository.data";
	public static final String DATA_VALUE = "/var/rhjoerg-maven-repository";

	public static int getPort()
	{
		return Integer.parseInt(System.getProperty(PORT_KEY, PORT_VALUE));
	}

	public static String getData()
	{
		return System.getProperty(DATA_KEY, DATA_VALUE);
	}
}
