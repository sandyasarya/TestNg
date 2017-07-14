package TestClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import JavaCode.CommonAction;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import javax.net.ssl.HttpsURLConnection;


public class NewTest extends JavaCode.CommonAction
{
	
	@BeforeMethod
	public void Beforetest(Method method) throws IOException, InterruptedException
	{
		// String[] credential=(ReadCredentials(testName.toString()).toString()).split("\n");	
	     System.out.println("Test name: " + method.getName());  		
	}
     
	@Test 
    public void AbilitiesTest() throws IOException, InterruptedException
     {
    	try
    	{		String url1="http://maps.googleapis.com/maps/api/geocode/json?address=chicago&sensor=false&#8221;";
		URL url = new URL(url1);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	conn.setRequestProperty("Accept", "application/json");

	//HttpsURLConnection con1 = (HttpsURLConnection)url.openConnection();

	if (conn.getResponseCode() != 200) 
	{
	throw new RuntimeException(" HTTP error code :" + conn.getResponseCode());
	}

	Scanner scan = new Scanner(url.openStream());
	String entireResponse = new String();
	while (scan.hasNext())
	entireResponse += scan.nextLine();

	System.out.println("Response : "+entireResponse);

	scan.close();

	JSONObject obj = new JSONObject(entireResponse );
	String responseCode = obj.getString("status");
	System.out.println("status : " + responseCode);

	JSONArray arr = obj.getJSONArray("results");
	for (int i = 0; i < arr.length(); i++) {
	String placeid = arr.getJSONObject(i).getString("place_id");
	System.out.println("Place id : " + placeid);
	String formatAddress = arr.getJSONObject(i).getString("formatted_address");
	System.out.println("Address : " + formatAddress);

	//validating Address as per the requirement
	if(formatAddress.equalsIgnoreCase("Chicago, IL, USA"))
	{
	System.out.println("Address is as Expected");
	}
	else
	{
	System.out.println("Address is not as Expected");
	}
	}

	conn.disconnect();
	} catch (MalformedURLException e) {
	e.printStackTrace();

	} catch (IOException e) {

	e.printStackTrace();

	}
     }
	@Test 
    public void AbilitiesTest1() throws IOException, InterruptedException
     {
    		
    		  {   			  
    			  CommonAction.App();
    			  
    			 //Assert.assertTrue(false);
    		  }
     }
}
