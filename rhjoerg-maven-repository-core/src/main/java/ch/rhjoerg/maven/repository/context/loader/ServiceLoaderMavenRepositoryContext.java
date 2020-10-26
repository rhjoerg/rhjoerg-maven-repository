package ch.rhjoerg.maven.repository.context.loader;

import java.util.ServiceLoader;

import ch.rhjoerg.maven.repository.context.MavenRepositoryContext;
import ch.rhjoerg.maven.repository.service.MavenRepositoryService;

public class ServiceLoaderMavenRepositoryContext implements MavenRepositoryContext
{
	private MavenRepositoryService service;

	@Override
	public synchronized MavenRepositoryService getService()
	{
		if (service == null)
		{
			service = ServiceLoader.load(MavenRepositoryService.class).findFirst().get();
		}

		return service;
	}
}
