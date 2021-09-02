package com.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;

public class JvmReportMobile extends CapabilitiesAndWebDriverUtils{
		
		public static void generateReport(String JsonFile) throws IOException {
			
			Configuration config = new Configuration(new File (System.getProperty("user.dir")+"/target/ResultsMobile"), "DD book CLub  Automation report");
			config.addClassifications("Iteration", "1");
			config.addClassifications("Platform	",getData("pName"));
			config.addClassifications("platformVersion",getData("pVersion"));
			config.addClassifications("Build", getData("build"));
			config.addClassifications("deviceName", getData("devName"));
			
			List <String> JsonFiles = new ArrayList<String>();
			JsonFiles.add(JsonFile);
			ReportBuilder report = new ReportBuilder(JsonFiles, config);
			report.generateReports();
			 
		}
		

}
