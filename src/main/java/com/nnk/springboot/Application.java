package com.nnk.springboot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Application v1.0:
 *  -
 *  Spring boot service
 *  Jpa repositories
 *  Thymeleaf frontend
 *  Bootstrap html5/css3
 *
 *  @author xGuix
 *  @version v1.0
 */
@SpringBootApplication
public class Application
{
	private static final Logger loggerMain = LogManager.getLogger("PoseidenIncApplication");

	/**
	 *  Starting app
	 *
	 *  @param args String args
	 */
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
		loggerMain.info("Poseiden application has started");
	}
}