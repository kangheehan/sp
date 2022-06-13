package sp.jetty.client;

import java.util.concurrent.TimeUnit;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.http.HttpMethod;

import sp.utils.Logger;

public class MyHttpClient {
	private HttpClient httpClient;
    
	public MyHttpClient() {
		httpClient = new HttpClient();
	}
	
	public void send(String uri) throws InterruptedException {
		Logger.print("Sending " + uri + " ...");
		httpClient.newRequest(uri)
			.method(HttpMethod.GET)
			.timeout(5, TimeUnit.SECONDS)
			.send(new BufferingResponseListener() {

				@Override
				public void onComplete(Result result) {
					if (result.isSucceeded()) {
						Logger.print(getContentAsString());
					} else {
						Logger.print("Failed");
						Logger.print(result);
					}
				}
			});
	}
	
	public HttpClient getHttpClient() {
		return httpClient;
	}
	
	public void start() throws Exception {
		httpClient.start();
	}
	
	public void stop() throws Exception {
		httpClient.stop();
	}
	
	public static void main(String[] args) throws Exception {
		MyHttpClient client = new MyHttpClient();
		client.start();
		for(int i=0; i<3; i++) {
			client.send("http://127.0.0.1:8080/mypath");
			client.send("http://127.0.0.1:8080/mypath2");
		}
//		client.stop();
	}
}
