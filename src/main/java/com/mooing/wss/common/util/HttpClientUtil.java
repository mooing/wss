package com.mooing.wss.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentProducer;
import org.apache.http.entity.EntityTemplate;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientUtil {
	/**
	 * 常量 log.
	 */
	private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

	public static void urlConnectionPost() {
		StringBuilder responseBuilder = null;
		BufferedReader reader = null;
		OutputStreamWriter wr = null;

		URL url;
		try {
			url = new URL("");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write("");
			wr.flush();

			// Get the response
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			responseBuilder = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseBuilder.append(line + "\n");
			}
			wr.close();
			reader.close();

			System.out.println(responseBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String urlConnectionPost(String urlStr, String xmlStr) {

		StringBuilder responseBuilder = new StringBuilder();
		BufferedReader reader = null;
		OutputStreamWriter wr = null;

		URL url;
		try {
			log.info("HttpClient |urlConnectionPost , request xml:" + xmlStr);
			url = new URL(urlStr);
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(1000 * 5);
			wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(xmlStr);
			wr.flush();

			// Get the response
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				responseBuilder.append(line + "\n");
			}
			wr.close();
			reader.close();

			log.info("HttpClient |urlConnectionPost , response:" + responseBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return responseBuilder.toString();
	}

	public static String httpClientPost(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);

		try {
			ContentProducer cp = new ContentProducer() {
				public void writeTo(OutputStream outstream) throws IOException {
					Writer writer = new OutputStreamWriter(outstream, "UTF-8");
					writer.write("");
					writer.flush();
				}
			};

			post.setEntity(new EntityTemplate(cp));

			HttpResponse response = client.execute(post);
			String ret = EntityUtils.toString(response.getEntity());
			System.out.println(ret);
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static String httpClientGet(String url) {
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);

		try {
			HttpResponse response = client.execute(get);
			if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
				String ret = EntityUtils.toString(response.getEntity());
				System.out.println(ret);
				if (ret == null) {
					return "";
				}
				return ret;
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void main(String args[]) {
		// //
		// HttpClient.httpClientPost("http://211.151.75.229:8083/api58.ashx?date=20120606&mode=debug");
		// try {
		// //
		// HttpClient.httpClientPost2("http://balance.58.com/httpRequest/getResponseByUserId?userId=6649766860289");
		// Map<String, String> params = new HashMap<String, String>();
		// params.put("name", "jinxin");
		// params.put("age", "1");
		// String response =
		// httpClientPost("http://127.0.0.1/requesttest/RequestTestServlet",
		// params);
		// System.out.println(response);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	public static String httpClientPost2(String url) throws ClientProtocolException, IOException {
		String result = "";
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		httppost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httppost);

		// 上面两行可以得到指定的Header
		if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
			result = EntityUtils.toString(response.getEntity());
		} else {
			log.error("httpClientPost2 error! url=" + url + " ,errorCode=" + response.getStatusLine().getStatusCode());
		}
		return result;
	}

	public static String httpClientPost(String url, Map<String, Object> params) throws IOException {
		String result = "";
		HttpPost httppost = new HttpPost(url);
		log.info("~~~~~~~~url=" + url + "~~~~~~~~");
		List<NameValuePair> paramsPair = new ArrayList<NameValuePair>();

		// 设置Http Post数据
		if (params != null) {

			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				log.info("~~~~~~~~" + entry.getKey() + "=" + entry.getValue() + "~~~~~~~~");
				paramsPair.add(pair);
			}
		}
		httppost.setEntity(new UrlEncodedFormEntity(paramsPair, HTTP.UTF_8));
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httppost);

		// 上面两行可以得到指定的Header
		if (response.getStatusLine().getStatusCode() == 200) {// 如果状态码为200,就是正常返回
			result = EntityUtils.toString(response.getEntity());
			log.info("httpClientPost: url=" + url + " ,result =" + result);
		} else {
			log.error("httpClientPost error! url=" + url + " ,errorCode=" + response.getStatusLine().getStatusCode());
		}
		return result;
	}

	public static int statusOfHttpClientPost(String url, Map<String, String> params) throws IOException {
		HttpPost httppost = new HttpPost(url);
		List<NameValuePair> paramsPair = new ArrayList<NameValuePair>();
		log.info("~~~~~~~~url=" + url + "~~~~~~~~");
		// 设置Http Post数据
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
				log.info("~~~~~~~~" + entry.getKey() + "=" + entry.getValue() + "~~~~~~~~");
				paramsPair.add(pair);
			}
		}
		httppost.setEntity(new UrlEncodedFormEntity(paramsPair, HTTP.UTF_8));
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response = httpclient.execute(httppost);

		return response.getStatusLine().getStatusCode();
	}
}
