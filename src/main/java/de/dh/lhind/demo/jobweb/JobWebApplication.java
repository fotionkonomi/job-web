package de.dh.lhind.demo.jobweb;

import org.apache.log4j.BasicConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JobWebApplication {

	public static void main(String[] args)
	{
		BasicConfigurator.configure();
		SpringApplication.run(JobWebApplication.class, args);
	}

}
