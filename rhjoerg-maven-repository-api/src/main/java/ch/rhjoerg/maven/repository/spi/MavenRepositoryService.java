package ch.rhjoerg.maven.repository.spi;

public interface MavenRepositoryService
{
	public static interface Result
	{
		public boolean isListing();

		public boolean isData();

		public String[] getListing();

		public byte[] getData();
	}

	public void setStorage(MavenRepositoryStorage storage);

	public Result getResult(String path);
}
