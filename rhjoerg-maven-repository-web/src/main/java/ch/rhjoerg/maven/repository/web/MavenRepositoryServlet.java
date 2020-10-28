package ch.rhjoerg.maven.repository.web;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.rhjoerg.maven.repository.spi.MavenRepositoryContext;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryService;
import ch.rhjoerg.maven.repository.spi.MavenRepositoryService.Result;

public class MavenRepositoryServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	private MavenRepositoryContext getContext()
	{
		return MavenRepositoryContext.class.cast(getServletContext().getAttribute(MavenRepositoryContext.class.getName()));
	}

	private MavenRepositoryService getService()
	{
		return getContext().getService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Result result = getService().getResult(request.getPathInfo());

		if (result == null)
		{
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);

			return;
		}

		if (result.isListing())
		{
			renderListing(result.getListing(), response);
		}
		else
		{
			sendData(result.getData(), response);
		}
	}

	private void renderListing(String[] listing, HttpServletResponse response) throws IOException
	{
		String html = Stream.of(listing) //
				.map(s -> "<a href='" + s + ">" + s + "</a>") //
				.collect(Collectors.joining("<br/>", "", ""));

		sendContent("text/html;charset=utf-8", html.getBytes(StandardCharsets.UTF_8), response);
	}

	private void sendData(byte[] data, HttpServletResponse response) throws IOException
	{
		sendContent("application/octet-stream", data, response);
	}

	private void sendContent(String contentType, byte[] data, HttpServletResponse response) throws IOException
	{
		response.setContentType(contentType);
		response.setContentLength(data.length);
		response.getOutputStream().write(data);
	}
}
