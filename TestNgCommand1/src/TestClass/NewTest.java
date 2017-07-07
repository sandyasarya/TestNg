package TestClass;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import JavaCode.CommonAction;


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
    		
    		  {   			  
    			  CommonAction.App();
    			  
    			 Assert.assertTrue(false);
    		  }
     }
	@Test 
    public void AbilitiesTest1() throws IOException, InterruptedException
     {
    		
    		  {   			  
    			  CommonAction.App();
    			  
    			 //ssert.assertTrue(false);
    		  }
     }
}
