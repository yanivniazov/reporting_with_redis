package application.dto;

public class EchoAtTimeResponse {
	
	private long time;
	private String message;
	private String description;
	
	
	
	public EchoAtTimeResponse( long time, String message, String description) {
		super();
		this.message = message;
		this.time = time;
		this.description = description;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
