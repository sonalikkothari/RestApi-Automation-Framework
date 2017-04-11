package com.wbl.restApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.testng.annotations.Test;

import com.wbl.base.HttpBaseApi;
import com.wbl.helper.ExcelUtils;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.http.*;;

public class TwitterRestApi extends HttpBaseApi {

	public HttpResponse getData(String url) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		
		HttpResponse response = null;
	
		HttpGet get = getResource(url);
		
		get.addHeader("Accept", "application/json");
		//get.addHeader("Content-Type", "application/json");
	
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer("TInTjAqGRvlWzfg0XMUewoDit", "JAykowkIT8HYFtAJ7NBiTFOCmyHkcDrKiMvxBnQEJedjNu8Nap");
		consumer.setTokenWithSecret("850590542937309185-QV5K5ddJBmTAUNRFEuZrMFkiIelhs5Y", "5JLQIgFkTC1kQVu7QsuKcm6YdoFvszCNan1KuXHUOP128");
		consumer.sign(get);
		
		 response = client.execute(get);
		
		return response;
	}
	//**************************************************
	
	public HttpResponse postData(String resource,List<? extends NameValuePair> nvps) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		
		HttpResponse response= null;
		
		HttpPost post = postDt(resource);
		 
		/*for(NameValuePair disp: nvps)
			System.out.println(disp.getName()+"      "+disp.getValue());*/
	
		post.setEntity(new UrlEncodedFormEntity(nvps));
		
		
		OAuthConsumer consumer = new CommonsHttpOAuthConsumer("TInTjAqGRvlWzfg0XMUewoDit", "JAykowkIT8HYFtAJ7NBiTFOCmyHkcDrKiMvxBnQEJedjNu8Nap");
		consumer.setTokenWithSecret("850590542937309185-QV5K5ddJBmTAUNRFEuZrMFkiIelhs5Y", "5JLQIgFkTC1kQVu7QsuKcm6YdoFvszCNan1KuXHUOP128");
		consumer.sign(post);
		response = client.execute(post);
		return response;

	}
}

