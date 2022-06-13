package sp.jetty.client;

import org.eclipse.jetty.client.HttpClient;

public class MultiClient {
	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();
		httpClient.start();
		
		ClientThread t1 = new ClientThread();
		ClientThread t2 = new ClientThread();
		
		t1.setHttpClient(httpClient);
		t1.setRequest("http://127.0.0.1:8080/mypath");
		
		t2.setHttpClient(httpClient);
		t2.setRequest("http://127.0.0.1:8080/mypath2");

		t1.start();
		t2.start();
		t1.join();
		t2.join();
		httpClient.stop();
	}
}
