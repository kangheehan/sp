package sp.jetty.client;

public class MultiClient3 {
	public static void main(String[] args) throws Exception {
		MyHttpClient client = new MyHttpClient();
		client.send("http://127.0.0.1:8080/mypath");
		client.send("http://127.0.0.1:8080/mypath2");
	}
}
