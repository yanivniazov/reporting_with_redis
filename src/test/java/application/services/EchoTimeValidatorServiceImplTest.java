package application.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import application.dto.ValidatorResponse;

@SpringBootTest
public class EchoTimeValidatorServiceImplTest {
	 
	 @Autowired
	 EchoTimeValidatorService echoTimeValidatorService;
	 
	 @Test
	 public void testTimeInThePasgEchoTimeValid(){
		 ValidatorResponse isValid = echoTimeValidatorService.isEchoTimeValid(1l, "yaniv");	 
		 assertEquals(false,isValid.getIsValid());
	 }
	 
	 
	 @Test
	 public void testNotValidTimeEchoTimeValid(){
		 ValidatorResponse isValid = echoTimeValidatorService.isEchoTimeValid(-1l, "yaniv");	 
		 assertEquals(false,isValid.getIsValid());
	 }
	 
	 
	 
	 @Test
	 public void testValidTimeEchoTimeValid(){
		 ValidatorResponse isValid = echoTimeValidatorService.isEchoTimeValid(10000000000000l, "yaniv");	// Saturday, 20 November 2286 17:46:40 
		 assertEquals(true,isValid.getIsValid());
	 }
	 
	 
	 @Test
	 public void testInvalidMessageTimeEchoTimeValid(){
		 ValidatorResponse isValid = echoTimeValidatorService.isEchoTimeValid(10000000000000l, "");	// Saturday, 20 November 2286 17:46:40 
		 assertEquals(false,isValid.getIsValid());
	 }
}