package sp.jetty.client;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.client.util.BufferingResponseListener;
import org.eclipse.jetty.http.HttpMethod;

public class MultiClient2 {
	public static void main(String[] args) throws Exception {
		HttpClient client = new HttpClient();
        client.start();

        final AtomicReference<Response> responseRef = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);

        client.newRequest("http://127.0.0.1:8080/mypath")
        	.method(HttpMethod.GET)
        	.send(new BufferingResponseListener() {

                    @Override
                    public void onComplete(Result result) {
                        if (result.isSucceeded()) {
                        	
                        	System.out.println(this.getContentAsString());
                            responseRef.set(result.getResponse());
                            latch.countDown();
                        }
                    }
                });

        boolean val = latch.await(5, TimeUnit.SECONDS);
        
        if (!val) {
            System.out.println("Time has elapsed.");
            System.exit(1);
        }
        
        Response response = responseRef.get();
        System.out.println(response.getClass());
        System.out.println(response.getStatus());
        
        client.stop();
	}
	
	
}
