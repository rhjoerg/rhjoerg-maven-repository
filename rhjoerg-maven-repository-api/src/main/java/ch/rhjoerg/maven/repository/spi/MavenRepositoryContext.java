package ch.rhjoerg.maven.repository.spi;

public interface MavenRepositoryContext
{
	public MavenRepositoryStorage getStorage();

	public MavenRepositoryService getService();
}
