package application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import application.dto.EchoAtTimeResponse;
import application.dto.ValidatorResponse;
import application.services.EchoAtTimeService;
import application.services.EchoTimeValidatorService;


/**
 * 
 * @author yaniv.n
 * A controller that expose an API for echoAtTIme.
 * it adds a new message only if the message is valid and updates the next message to process.
 */
@RestController
public class EchoAtTimeController {
	
	Logger logger = LoggerFactory.getLogger(EchoAtTimeController.class);
	
	@Autowired
	EchoTimeValidatorService echoTimeValidatorService;
	
	@Autowired
	EchoAtTimeService echoAtTimeService;
	
	
	@GetMapping( "/echoAtTime")
	public EchoAtTimeResponse  echoAtTime(@RequestParam(value = "time") long time, 
							 @RequestParam(value = "message")  String message) {
		
		logger.info("Adding new Message to EchoAtTime");

		EchoAtTimeResponse echoAtTimeResponse;
		
		//check if time is valid
		ValidatorResponse validatorResponse = echoTimeValidatorService.isEchoTimeValid(time, message);
		if(!validatorResponse.getIsValid()) {
			echoAtTimeResponse = new EchoAtTimeResponse(time, message, "Can't add a message to EchoAtTime Engine ," + validatorResponse.getDescription());
			return echoAtTimeResponse;
			
		}
		//add the message to the EchoAtTime Engine	
		echoAtTimeService.addNewMessage(time, message);
		echoAtTimeResponse = new EchoAtTimeResponse(time, message, "New Message  was  added successfully to EchoAtTime engine!");
		
		logger.info("New Message was added successfully :"   + message);
		

		return echoAtTimeResponse;
	}
}
