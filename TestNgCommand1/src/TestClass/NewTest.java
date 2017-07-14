package TestClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Arrays;

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
    		
    		  {   			  //TestNgCommand1\src\TestClass\Test.jmx
    			  
    			  //  String[] command = {"CMD", "/C", "jmeter -g D:\\apache-jmeter-3.2\\apache-jmeter-3.2\\bin\\result.jtl -o C:\\Users\\sandesh\\Desktop\\SS1"};
    		  String[] command = {"CMD", "/C", "jmeter -n -t ${TRAVIS_BUILD_DIR}\\TestNgCommand1\\Test.jmx -l ${TRAVIS_BUILD_DIR}\\TestNgCommand1\\result.jtl -e -o ${TRAVIS_BUILD_DIR}\\TestNgCommand1"};
    		        ProcessBuilder probuilder = new ProcessBuilder( command );
    		        //You can set up your work directory
    		        probuilder.directory(new File("D:\\apache-jmeter-3.2\\apache-jmeter-3.2\\bin"));
    		        
    		        Process process = probuilder.start();
    		        
    		        //Read out dir output
    		        InputStream is = process.getInputStream();
    		        InputStreamReader isr = new InputStreamReader(is);
    		        BufferedReader br = new BufferedReader(isr);
    		        String line;
    		        System.out.printf("Output of running %s is:\n", Arrays.toString(command));
    		        while ((line = br.readLine()) != null) {
    		            System.out.println(line);
    		        }
    		        
    		        //Wait to get exit value
    		        try {
    		            int exitValue = process.waitFor();
    		            System.out.println("\n\nExit Value is " + exitValue);
    		        } catch (InterruptedException e) {
    		            // TODO Auto-generated catch block
    		            e.printStackTrace();
    		        }
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
