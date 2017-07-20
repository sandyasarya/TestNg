package TestClass;

import static org.testng.Assert.fail;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class AdminTest 
{
	public String logMessage = null;
	public int responseCode = 0;
	String Authorization_Token=null;	
	ExtentReports extent = new ExtentReports();		
	ExtentTest Test;	
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Results\\extentReportFile_"+timeStamp+".html");
	
	
	@BeforeTest
	public void EnvironmentSet()
	{
		 extent.setSystemInfo("Host Name", "Mobiliya");  
    	 extent.setSystemInfo("Environment", "Automation Testing");
         extent.setSystemInfo("User Name", "Sandesh");
    	 extent.attachReporter(htmlReporter);     
	}
    
    @BeforeMethod
	public void startReport(Method method)
	{	   		
    	 Test = extent.createTest(method.getName()," ");	   	
	}
    @Test
    public void Sample()
    {
    	System.out.println("this is basic test");
    }

	@Test
	private void AdminLoginValidCredentials() throws Exception 
	{
		 try{		    
		    Test.log(Status.INFO, "Start Test");		 
			URL url = new URL("http://localhost:3000/login");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");		
			con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
			Test.log(Status.INFO, "Sent valid credentials");		
			String urlParameters = "grant_type=password&username=admin@test.com&password=admin&client_id=thom&client_secret=nightworld";
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);
			
			
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			String responseMessage=con.getResponseMessage();
//			System.out.println("Sending 'POST' request to URL : " + url);		
//			System.out.println("Response Code : " + responseCode);
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = in.readLine()) != null) 
			{
				response.append(inputLine);
			}
			in.close();
			
			if (con.getResponseCode() != 200) 
			{
			 Test.log(Status.INFO, "Verify response code and its wrong : "+responseCode);	
			 throw new RuntimeException(" HTTP error code : " + con.getResponseCode());
			
			}
			else
			{
				 //Assert.assertEquals(200, responseCode); 
				 Test.log(Status.INFO, "Verify response code and its correct : "+responseCode);	
			     String[] parts = response.toString().split("[:|,|\"|]");
			     //System.out.println(parts[10]);
			     Authorization_Token=parts[10];	
			     Test.log(Status.INFO, "Stored Authorization_Token ");			     
			}				
		 }		
		 catch(Exception e)
		  {
			 logMessage = e.getMessage();	
			 Assert.fail();
	      }		 
		 catch(AssertionError e)
		  {
			logMessage = "HTTP error code : "+responseCode;
			Assert.fail();
	      }
		
		}


	@Test(enabled=false)
	private void AdminLoginInValidCredentials() throws Exception 
	{
		try{
		    Test.log(Status.INFO, "using In-valid credentials ");		
			URL url = new URL("http://localhost:3000/login");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");		
			con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
			Test.log(Status.INFO, "Sent In-valid credentials");	
			String urlParameters = "grant_type=password&username=admin@1test.com&password=admin&client_id=thom&client_secret=nightworld";
			
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

		    responseCode = con.getResponseCode();
			String responseMessage=con.getResponseMessage();
			//System.out.println("Sending 'POST' request to URL : " + url);		
			//System.out.println("Response Code : " + responseCode);			
			if (con.getResponseCode() != 401) 
			{
			Test.log(Status.FAIL, "Verify response code and its wrong :  "+responseCode);
			throw new RuntimeException(" HTTP error code : " + con.getResponseCode());
			}
			else
			{
				//Assert.assertEquals(401, responseCode); 
				Test.log(Status.INFO, "Verify response code and its correct == 401");	
			}
		 }
		catch(Exception e)
		  {
			logMessage = e.getMessage();
			Assert.fail();
	      }
		catch(AssertionError e)
		  {
			logMessage = "HTTP error code : "+responseCode;
			Assert.fail();
	      }
	     }

	
	@Test(priority=3)//,enabled=false)
	private void Addingdevice() throws Exception 
	   {
	        try
	        {
			Test.log(Status.INFO, "Adding device to white list ");		
			URL url = new URL("http://localhost:3000/device_whitelist");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");		
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty ("Content-Type", "application/json; charset=UTF-8"); 
			Test.log(Status.INFO, "Adding authorization token with bearer ");	
		    con.setRequestProperty("authorization", "Bearer " + Authorization_Token);
		    Test.log(Status.INFO,"Adding device as parameter ");
		    String urlParameters = "{\"devicesIds\":[{\"deviceId\":\"sandy12\"},{\"deviceId\":\"asandy7888\"}]}";
			
		    con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			String re=con.getResponseMessage();
			System.out.println("\n Sending 'POST' request to URL : " + url);		
			System.out.println("Response Code : " + responseCode);		
			if (con.getResponseCode() != 201) 
			{
			 Test.log(Status.FAIL, "Verify response code and its wrong :  "+responseCode);
			 throw new RuntimeException(" HTTP error code : " + con.getResponseCode());				
			}
			else 
			{
				Assert.assertEquals(201, responseCode);
				Test.log(Status.INFO, "Verify response code and its correct : "+responseCode);	
//				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) 
//				{
//					response.append(inputLine);
//				}
//				in.close();			
				
			}
	        }			
			catch(Exception e)
			  {
				logMessage = e.getMessage();
				Assert.fail();
		      }
	        catch(AssertionError e)
			  {
				logMessage = "HTTP error code : "+responseCode;
				Assert.fail();
		      }
		}
	
	
	@Test(priority=4)
	private void Retrivedevice() throws Exception 
	   {
	        try
	        {
			Test.log(Status.INFO, "Retrive device to white list ");	
			Test.log(Status.INFO,"adding query parameter");
			String query = String.format("?limit5",URLEncoder.encode("limit", "UTF-8"));
			URL url = new URL("http://localhost:3000/device_whitelist");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");		
			con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
			con.setRequestProperty ("Content-Type", "application/json; charset=UTF-8"); 
			Test.log(Status.INFO, "Adding authorization token with bearer ");	
		    con.setRequestProperty("authorization", "Bearer " + Authorization_Token);
		    con.setDoInput(true);			 
	        con.setDoOutput(false);

			responseCode = con.getResponseCode();				
			if (con.getResponseCode() != 200) 
			{
			 Test.log(Status.FAIL, "Verify response code and its wrong :  "+responseCode);
			 throw new RuntimeException(" HTTP error code : " + con.getResponseCode());				
			}
			else 
			{
				//Assert.assertEquals(200, responseCode);
				Test.log(Status.INFO, "Verify response code and its correct : "+responseCode);	
//				BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
//				String inputLine;
//				StringBuffer response = new StringBuffer();
//				while ((inputLine = in.readLine()) != null) 
//				{
//					response.append(inputLine);
//				}
//				in.close();			
				
			}
	        }			
			catch(Exception e)
			  {
				logMessage = e.getMessage();
				Assert.fail();
		      }
	        catch(AssertionError e)
			  {
				logMessage = "HTTP error code : "+responseCode;
				Assert.fail();
		      }
		}
	
	
	@AfterMethod
	public void close(ITestResult result)
	{
		if (result.getStatus() == ITestResult.FAILURE)
		{	    
		Test.log(Status.FAIL,logMessage);
        Test.log(Status.FAIL,"Test failed due to above reason");
        
		}
        else if (result.getStatus() == ITestResult.SKIP)
        	Test.log(Status.SKIP,"Test SKIp");
		
        else
        	Test.log(Status.PASS,"Test PASS");
		
		extent.flush();	 
			
	}

}





