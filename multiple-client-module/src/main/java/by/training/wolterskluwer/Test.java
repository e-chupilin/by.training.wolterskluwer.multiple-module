package by.training.wolterskluwer;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.resource.URIResolver;
import org.apache.log4j.Logger;

/**
 * 
 * Simple client for test DocumentsRest service. In work i use SoapUI.
 * 
 */
public class Test {
	private static PostMethod post;
	private static final Logger LOGGER = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		InputStream in = null;
		URL url = null;
		PutMethod put = null;
		File input = null;
		String inputFile;
		URIResolver resolver;
		Test testClient;
		RequestEntity entity;
		HttpClient httpclient;

		try {
			LOGGER.info("Sent HTTP GET document. Id = 1");
			url = new URL("http://localhost:8080/DocumentsRest/documents/1");
			in = url.openStream();
			LOGGER.info(getStringFromInputStream(in));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}

		try {
			LOGGER.info("\n Sent HTTP PUT request to update document`s chapter. Document id=1.");
			testClient = new Test();
			inputFile = testClient.getClass().getResource("/chapter.xml").getFile();
			resolver = new URIResolver(inputFile);
			input = new File(resolver.getURI());
			put = new PutMethod("http://localhost:8080/DocumentsRest/documents/1");
			entity = new FileRequestEntity(input, "application/xml");
			put.setRequestEntity(entity);
			httpclient = new HttpClient();

			int result = httpclient.executeMethod(put);

			LOGGER.info("Response status code: " + result);
			LOGGER.info(put.getResponseBodyAsString());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (put != null) {
				put.releaseConnection();
			}
		}

		try {
			LOGGER.info("\n");
			LOGGER.info("Sent HTTP POST request to add document");
			testClient = new Test();
			inputFile = testClient.getClass().getResource("/document.xml").getFile();
			resolver = new URIResolver(inputFile);
			input = new File(resolver.getURI());
			post = new PostMethod("http://localhost:8080/DocumentsRest/documents");
			post.addRequestHeader("Accept", "application/xml");
			entity = new FileRequestEntity(input, "application/xml");
			post.setRequestEntity(entity);
			httpclient = new HttpClient();

			int result = httpclient.executeMethod(post);
			LOGGER.info("Response status code: " + result);
			LOGGER.info(post.getResponseBodyAsString());

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			if (put != null) {
				put.releaseConnection();
			}
		}

		try {
			LOGGER.info("\n Sent HTTP GET document. Id = 2");
			url = new URL("http://localhost:8080/DocumentsRest/documents/2");
			in = url.openStream();
			LOGGER.info(getStringFromInputStream(in));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	private static String getStringFromInputStream(InputStream in) throws Exception {
		CachedOutputStream bos = new CachedOutputStream();
		IOUtils.copy(in, bos);
		in.close();
		bos.close();
		return bos.getOut().toString();
	}

}