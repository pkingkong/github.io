import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientDemo{

	public static void main(String[] args){
		// TODO Auto-generated method stub

		// 프록시를 태워야되는 경우에만 설정
		String proxyHost = "";
		String proxyPort = "";

		HttpClientBuilder httpClient = HttpClientBuilder.create();
		httpClient.setProxy(new HttpHost(proxyHost, Integer.parseInt(proxyPort)));

		Map<String, Object> headers = new HashMap<String, Object>();
		Map<String, Object> parameters = new HashMap<String, Object>();		

		headers.put("", "");
		parameters.put("", "");

		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for (Map.Entry<String, Object> parameter : parameters.entrySet()){
			nvps.add(new BasicNameValuePair(parameter.getKey(), parameter.getValue().toString()));
		}

		String url = "";
		String method = "";
		HttpRequestBase request = null;

		if (method.equals("GET")){
			HttpGet httpGet = new HttpGet(url);
			request = httpGet;
		} else if (method.equals("POST")){
			HttpPost httpPost = new HttpPost(url);
			try{
				httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			} catch (UnsupportedEncodingException e){

			}
			request = httpPost;
		}

		for (Map.Entry<String, Object> header : headers.entrySet()){
			request.setHeader(header.getKey(), header.getValue().toString());
		}

		try{
			HttpResponse httpResponse = httpClient.build().execute(request);
		} catch (IOException e){

		}

	}

}
