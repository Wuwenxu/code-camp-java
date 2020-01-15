package net.jerryfu.http;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * http get/post工具类
 * @author jerryfu
 *
 */
public final class HttpUtils {

	private static final String CHARSET_UTF_8 = "UTF-8";

	public static String get(String url) {
		System.out.println(url.trim());
		HttpGet httpGet = new HttpGet(url.trim());
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(5000).build();
		httpGet.setConfig(requestConfig);
		try {
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpGet);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static String post(String url, String param) {
		System.out.println(url.trim());
		System.out.println(param);
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
			httpPost.setConfig(requestConfig);
			StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
			httpPost.setEntity(se);
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String postForm(String url,Map<String, String> params) {
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000).setConnectionRequestTimeout(30000).setSocketTimeout(30000).build();
			httpPost.setConfig(requestConfig);
			//StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
			 List<NameValuePair> form = new ArrayList<NameValuePair>();
			 for (String name : params.keySet()) {  
				 form.add(new BasicNameValuePair(name, params.get(name)));  
			 	}
			 UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form,  
					 CHARSET_UTF_8);
			httpPost.setEntity(entity);
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) {
		System.out.println(get("http://xxx.com/xxx"));
	}

}
