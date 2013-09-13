package com.gemantic.gemantic.weibo.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import java.net.URISyntaxException;
import java.util.Arrays;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class HttpClientUtil {

	private static final Log log = LogFactory.getLog(HttpClientUtil.class);

	public static String get(String url) throws URISyntaxException, URIException {
		

		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		// Create a method instance.
		GetMethod method = new GetMethod();
		URI uri=new URI(url.toString(), false, "UTF-8");
		method.setURI(uri);

		try {
			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				log.error("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();
			InputStream is = method.getResponseBodyAsStream();
			String[] charsets = CharsetDetectorUtil.getInstance()
					.detectAllCharset(is);

			log.info(url + " get charset " + Arrays.asList(charsets));
			String charset = "utf-8";
			if (charsets.length > 0) {
				charset = charsets[0];
			}

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			return new String(responseBody, charset);

		} catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		} finally {
			// Release the connection.
			method.releaseConnection();
		}
		return null;
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException, URIException, URISyntaxException {
		String url="http://www.bocichina.com/boci/f10/f10Content.jsp?tn=GSGG&guid={89EE1654-9E8A-45E5-98F1-D34BB52CA4AF}";

		String content=HttpClientUtil.get(url);
		log.info(content);
		
	}
}
