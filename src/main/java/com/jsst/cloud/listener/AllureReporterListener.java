package com.jsst.cloud.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class AllureReporterListener implements IHookable{
	private static final Logger logger = LoggerFactory
			.getLogger(AllureReporterListener.class);
	
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                takeScreenShot(testResult.getMethod().getMethodName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    } 
    
    /*
    @Attachment(value = "Failure in method {0}", type = "image/png") 
    private byte[] takeScreenShot(String methodName) throws Exception{
        File screenshot = Screenshots.getLastScreenshot();
        return Files.toByteArray(screenshot);
    	
    }
    */
    
    private void takeScreenShot(String methodName) throws Exception{
    	logger.info("调用监听器错误,获取截屏记录:{}",methodName);
    }
}
