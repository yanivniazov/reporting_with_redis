package application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import application.services.EchoAtTimeService;

@SpringBootApplication
@EnableScheduling
public class Application {

	Logger logger = LoggerFactory.getLogger(Application.class);
	@Autowired
	EchoAtTimeService echoAtTimeService;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			logger.info("Running EchoApplicaton");
			echoAtTimeService.printOldMessages();
			echoAtTimeService.readNextMessage();
		};
	}

}