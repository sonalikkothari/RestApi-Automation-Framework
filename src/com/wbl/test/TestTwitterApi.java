package com.wbl.test;
 

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.wbl.helper.ExcelUtils;
import com.wbl.restApi.TwitterRestApi;
//import com.wbl.restApi.main.RestApiExecuter;

import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;


public class TestTwitterApi {

	@Test
		public void testGetData() throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		
		TwitterRestApi rs =  new TwitterRestApi();
		
		//HttpResponse response =  rs.httpGet("https://api.dev.talentscreen.io/api/v1/common/levels?authentication=false&cache=disabled");		
		//HttpResponse response = rs.httpGet("https://itunes.apple.com/search?term=jim+jones&country=ca");
		HttpResponse response = rs.getData("account/settings.json");
		int actual =response.getStatusLine().getStatusCode();
		System.out.println("The response is ::" +actual);
		Assert.assertEquals(actual, 200);
		
		
		JSONObject jsonObj = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
		Object name = jsonObj.get("screen_name");
		System.out.println("screen_name is::"+name);
		Assert.assertEquals(name, "sonalikkothari", "failed");
		
		Object value = jsonObj.get("language");
		System.out.println("lang is::"+value);
		Assert.assertEquals(value, "en", "lang english failed");
		
	}
	//*************************************************
	

	@DataProvider(name="postdata")
	
	@Test
		public Object[][] testData() {
	return ExcelUtils.getExcelData("twitter-post", "postdata-twitter.xlsx");
	}
	
		@Test(dataProvider="postdata")
	
		public void testHttpPost(String resource,String name,String location) throws ClientProtocolException, IOException, OAuthMessageSignerException, OAuthExpectationFailedException, OAuthCommunicationException {
		
		TwitterRestApi rs =  new TwitterRestApi();
		
		List <NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("name", name));
		nvps.add(new BasicNameValuePair("location", location));
		
		HttpResponse response = rs.postData(resource,nvps);
		
		int actual =response.getStatusLine().getStatusCode();
		System.out.println("The response is ::" +actual);
		
		JSONObject jsonObj = new JSONObject(IOUtils.toString(response.getEntity().getContent()));
		
		Object namePost = jsonObj.get("name");
		System.out.println("name is::" +namePost);
		Assert.assertEquals(namePost, "kapil");

		Object value = jsonObj.get("location");
		System.out.println("location is::" +value);
		Assert.assertEquals(value, "cupertino ca");
		
	}

}
