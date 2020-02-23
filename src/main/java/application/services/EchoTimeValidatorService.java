package application.services;

import application.dto.ValidatorResponse;

public interface EchoTimeValidatorService {
	
	ValidatorResponse isEchoTimeValid(long time, String message);
}
