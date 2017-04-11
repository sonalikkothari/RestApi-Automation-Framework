package com.wbl.base;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpBaseApi {

		String endPoint = "https://api.twitter.com/1.1/";
		protected HttpClient client = HttpClientBuilder.create().build();
		
		public HttpGet getResource(String resource) {
			
			HttpGet get = new HttpGet(endPoint+resource);
			return get;
			
		}

		public HttpPost postDt(String resource) {
			
			HttpPost post = new HttpPost(endPoint+resource);
			return post;
			
		}

}
