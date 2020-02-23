package application.defintion;

public enum TimeValidationEnum {
	VALID("action is valid"), TIME_IN_THE_PAST("Time is in the past"),TIME_IS_NOT_VALID("time is not valid"),MESSAGE_IS_EMPTY("message is empty");
	
	private String value;

	private TimeValidationEnum(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
