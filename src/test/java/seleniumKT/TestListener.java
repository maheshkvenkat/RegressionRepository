package seleniumKT;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener 
{
	@Override		
    public void onFinish(ITestContext arg) 
	{					
        System.out.println(arg.getEndDate());			
        		
    }		

    @Override		
    public void onStart(ITestContext arg) 
    {					
        System.out.println(arg.getStartDate());				
        		
    }		

    @Override		
    public void onTestFailedButWithinSuccessPercentage(ITestResult res) 
    {					
        System.out.println("Failed Test case Name is "+res.getTestName());				
        		
    }		

    @Override		
    public void onTestFailure(ITestResult res) 
    {					
    	System.out.println("Failed Test case Name is "+res.getTestName());			
        		
    }		

    @Override		
    public void onTestSkipped(ITestResult res) 
    {					
    	System.out.println("Skipped Test case Name is "+res.getTestName());				
        		
    }		

    @Override		
    public void onTestStart(ITestResult res) 
    {					
    	System.out.println("Start Test case Name is "+res.getTestName());				
        		
    }		

    @Override		
    public void onTestSuccess(ITestResult res) 
    {					
    	System.out.println("Success Test case Name is "+res.getTestName());					
        		
    }		

}
