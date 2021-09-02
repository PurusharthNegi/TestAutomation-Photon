package com.base;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.tools.ant.taskdefs.WaitFor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Screenshots extends CapabilitiesAndWebDriverUtils{

//public static RemoteWebDriver driver;

	
	public static void takeScreenshot(WebDriver driver, String filename) throws IOException
	{
		waitFor(2000);
		File sc = (File)((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(sc, new File(filename));
	}
	
	
	public static void movetoFolder(String folder) throws IOException
	{
		String destFolder = folder.replace("Pass", "Fail");
	
		String source = folder;
		File srcDir = new File(source);

		String destination = destFolder;
		File destDir = new File(destination);
		
		if (destDir.exists())
		{
			FileUtils.deleteDirectory(destDir);
		}
		FileUtils.moveDirectory(srcDir, destDir);
	}
	
}