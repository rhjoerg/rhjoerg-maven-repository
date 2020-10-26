package ch.rhjoerg.maven.repository.context;

import ch.rhjoerg.maven.repository.service.MavenRepositoryService;

public interface MavenRepositoryContext
{
	public MavenRepositoryService getService();
}
