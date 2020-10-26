package ch.rhjoerg.maven.repository.service.standard;

import ch.rhjoerg.maven.repository.spi.MavenRepositoryService;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryStorage;

public class StandardMavenRepositoryService implements MavenRepositoryService
{
	@SuppressWarnings("unused")
	private MavenRepositoryStorage storage;

	@Override
	public void setStorage(MavenRepositoryStorage storage)
	{
		this.storage = storage;
	}
}
