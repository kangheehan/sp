package sp.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

public class JettyUtil {

	public static String getContent(HttpServletRequest req) throws IOException {
		byte[] buffer = new byte[req.getContentLength()];
		try (InputStream is = req.getInputStream()) {
			is.read(buffer);
		}

		return new String(buffer);
	}

	public static String getContentLineByLine(HttpServletRequest req) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()));
		String buffer;
		StringBuilder sb = new StringBuilder();
		while ((buffer = input.readLine()) != null) {
			sb.append(buffer).append(System.lineSeparator());
		}

		input.close();
		return sb.toString();
	}
}
