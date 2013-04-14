package org.marconfus.dino.misc;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;

public class StreamHelper {

	private static final Logger logger = Logger.getLogger(StreamHelper.class); 
	
	public static String parseURL(String url) throws ClientProtocolException,
			IOException {
		String streamUrl = url;

		if (url.endsWith(".m3u") || url.endsWith(".pls")) {

			logger.info("Stream " + url + " is a playlist.");
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				try {
					DataInputStream in = new DataInputStream(instream);
					BufferedReader br = new BufferedReader(
							new InputStreamReader(in));
					String strLine;
					while ((strLine = br.readLine()) != null) {
						logger.info("playlist contentline: " + strLine);
						if (strLine.indexOf("http://") > -1) {
							streamUrl = strLine.substring(strLine.indexOf("http://"));
							logger.info("found stream url " + streamUrl);
							break;
						}
					}
				} finally {
					instream.close();
				}
			}
		}

		return (streamUrl);
	}
}
