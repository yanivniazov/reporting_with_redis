package application.services;

public interface  EchoAtTimeService {
	
 void addNewMessage(long time, String message);
 void printOldMessages(); 
 public void readNextMessage();
 public void printNextMessageIfTimeArrived();
 
}
