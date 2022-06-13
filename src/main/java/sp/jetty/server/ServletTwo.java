package sp.jetty.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

public class ServletTwo extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println(req);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		res.setStatus(200);
		res.getWriter().write("Hello Two!");
	}
}
