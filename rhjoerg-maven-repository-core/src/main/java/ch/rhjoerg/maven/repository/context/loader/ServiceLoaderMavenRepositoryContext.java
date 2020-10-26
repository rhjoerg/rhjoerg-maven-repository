package ch.rhjoerg.maven.repository.context.loader;

import java.util.ServiceLoader;

import ch.rhjoerg.maven.repository.spi.MavenRepositoryContext;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryService;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryStorage;

public class ServiceLoaderMavenRepositoryContext implements MavenRepositoryContext
{
	private MavenRepositoryStorage storage;
	private MavenRepositoryService service;

	@Override
	public synchronized MavenRepositoryStorage getStorage()
	{
		if (storage == null)
		{
			storage = ServiceLoader.load(MavenRepositoryStorage.class).findFirst().get();
		}

		return storage;
	}

	@Override
	public synchronized MavenRepositoryService getService()
	{
		if (service == null)
		{
			service = ServiceLoader.load(MavenRepositoryService.class).findFirst().get();
			service.setStorage(getStorage());
		}

		return service;
	}
}
