package ch.rhjoerg.maven.repository.spi;

public interface MavenRepositoryProperties
{
	public static int getPort()
	{
		return Integer.parseInt(System.getProperty("rhjoerg-maven-repository.port", "80"));
	}
}
