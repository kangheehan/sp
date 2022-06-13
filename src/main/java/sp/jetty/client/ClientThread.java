package sp.jetty.client;

import java.util.Date;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;

public class ClientThread extends Thread {
	private String request;
	private HttpClient httpClient;
	
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}
	
	public void setRequest(String request) {
		this.request = request;
	}
	
	@Override
	public void run() {
		try {
			System.out.println(new Date() + " Sending " + request);
			ContentResponse contentRes = httpClient
					.newRequest(request)
					.method(HttpMethod.GET)
					.send();
			System.out.println(new Date() + " " + contentRes.getContentAsString());
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}
}
