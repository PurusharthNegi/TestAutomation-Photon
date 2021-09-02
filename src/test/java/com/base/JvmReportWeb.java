package com.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class JvmReportWeb {
		
	public static String ConfigurationFile = System.getProperty("user.dir") + "/Configs/Configuration.properties";
	static Configuration config = new Configuration(new File (System.getProperty("user.dir")+"/target/Results"), "RC_Smoke_WEB");
	
		public static void generateReport(String JsonFile) throws IOException {
			
			FileInputStream fis = new FileInputStream(ConfigurationFile);
			Properties prop = new Properties();
			prop.load(fis);
			String browserName = prop.getProperty("browser");
			if (browserName.equalsIgnoreCase("chrome")) {
			config.addClassifications("ENV", prop.getProperty("Env"));
			config.addClassifications("Build#", prop.getProperty("WebBuild"));
			config.addClassifications("Iteration", "1");
			config.addClassifications("Platform	", "Web");
			config.addClassifications("BrowserName", "Chrome");
			config.addClassifications("BrowserVersion", "90.0.4430.93");
			config.addClassifications("System Info", "Mac OS ");
			} else if(browserName.equalsIgnoreCase("safari")) {
				config.addClassifications("ENV", prop.getProperty("Env"));
				config.addClassifications("Build#", prop.getProperty("WebBuild"));
				config.addClassifications("Iteration", "1");
				config.addClassifications("Platform	", "Web");
				config.addClassifications("BrowserName", "safari");
				config.addClassifications("BrowserVersion", "14.0.3");
				config.addClassifications("System Info", "Mac OS ");
			}else if(browserName.equalsIgnoreCase("edge")) {
				config.addClassifications("ENV", prop.getProperty("Env"));
				config.addClassifications("Build#", prop.getProperty("WebBuild"));
				config.addClassifications("Iteration", "1");
				config.addClassifications("Platform	", "Web");
				config.addClassifications("BrowserName", "edge");
				config.addClassifications("BrowserVersion", "92.0.902.62");
				config.addClassifications("System Info", "Mac OS ");
			}
			
			List <String> JsonFiles = new ArrayList<String>();
			JsonFiles.add(JsonFile);
			ReportBuilder report = new ReportBuilder(JsonFiles, config);
			report.generateReports();
			 
		}
		

}
