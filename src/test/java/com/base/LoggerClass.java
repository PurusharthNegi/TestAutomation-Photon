package com.base;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerClass {

    //Initialize Log4j logs
	private static final Logger logger = LogManager.getLogger();

	  public static void main(String[] args) {
		  logger.info("Start of application");
	      logger.info("End of application");
	      logger.error("This is an error ");
	    }
	}
