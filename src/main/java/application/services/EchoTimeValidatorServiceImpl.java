package application.services;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import application.defintion.TimeValidationEnum;
import application.dto.ValidatorResponse;

/**
 * 
 * @author yaniv.n
 * Validate an epoch time sent from the user.
 * Validator should be used before a new request is sent by the user to add a new message to Echo.
 * if the time is in the past or negative, the class will return a not a valid message.
 * if the message is empty then the it return  a not valid message
 */
@Service
public class EchoTimeValidatorServiceImpl implements EchoTimeValidatorService  {
	
	Logger logger = LoggerFactory.getLogger(EchoTimeValidatorServiceImpl.class);
	
	public ValidatorResponse isEchoTimeValid(long time, String message) {
		
		ValidatorResponse validatorResponse = new ValidatorResponse();
				
		if(time < 0) {
			logger.info("time " + time + "can't be negetive");
			validatorResponse.setIsValid(Boolean.FALSE);
			validatorResponse.setDescription(TimeValidationEnum.TIME_IS_NOT_VALID.getValue());
			
			return validatorResponse;
		}
	
		long currentTime = Calendar.getInstance().getTimeInMillis();
		if( currentTime > time) {
			logger.info("time : " + time + " is in the past, current Time is: " + currentTime);
			validatorResponse.setIsValid(Boolean.FALSE);
			validatorResponse.setDescription(TimeValidationEnum.TIME_IN_THE_PAST.getValue());
			
			return validatorResponse;
		}
		
		if(message.equals("")) {
			logger.info("message is empty");
			validatorResponse.setIsValid(Boolean.FALSE);
			validatorResponse.setDescription(TimeValidationEnum.MESSAGE_IS_EMPTY.getValue());
			
			return validatorResponse;
		}
		
		logger.info("time : " + time + " is valid");
		validatorResponse.setIsValid(Boolean.TRUE);
		validatorResponse.setDescription(TimeValidationEnum.VALID.getValue());	
		
		return validatorResponse;
	}
}
