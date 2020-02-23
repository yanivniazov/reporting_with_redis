package application.services;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import application.repositories.MessageRepository;

/**
 * 
 * @author yaniv.n
 * This service is the core engine of EchoAtTime.
 * The service will execute a schedular that prompt a message when the time arrives.
 * if there are old messages that weren't processed, the engine will execute them before it executes future messages
 */
@Service
public class  EchoAtTimeServiceImpl implements EchoAtTimeService{

	
	@Autowired
	MessageRepository messageRepository;
	static private String nextMessage;
	
	Logger logger = LoggerFactory.getLogger(EchoAtTimeServiceImpl.class);
	
	public void addNewMessage(long time, String message) {
		messageRepository.addMessage(time, message);
		readNextMessage();
	}
	
	
	public void readNextMessage() {
	
		String nextMessageStr = messageRepository.getNextMessage();
		if(nextMessageStr == null) {
			logger.info("Reading next message : no new messages found , wait for the next update");
		} else {
			String[] strArr = nextMessageStr.split(":");
			logger.info("next message to print is " + strArr[1] + ", with time :" + strArr[0] );
		}
		
		nextMessage = nextMessageStr;
	}
	
	
	public void printOldMessages() {
		//before running the schedular  - get all old messages from Redis and print them .
		logger.info("printing old messages...");
		Set<String> allMessages = messageRepository.getAllOldMessages();
		
		Iterator<String> iter = allMessages.iterator();
		
		while (iter.hasNext()) {
			String oldMessage = iter.next();
			printAndRemoveMessage(oldMessage);
		}
		
		if(allMessages.isEmpty()) {
			logger.info("no old messages.");
		}
		
	}
	
	private void printAndRemoveMessage(String message) {
		printMessage(message);
		removeMessage(message);
	}
	
	//this method will print a message .
	private void printMessage(String message) {
		
		String[] strArr = message.split(":");
		logger.info("Printing message is  : " + strArr[1] + " which was set " +  strArr[0]);
	}
	
	// remove messages from set of messages in queue
	private void removeMessage(String message) {
		String[] strArr = message.split(":");
		logger.info("removing message  : " + strArr[1]);
		messageRepository.removeMessage(strArr[0], strArr[1]);
	}
	
	
	//scheduled every second - check if need to print a message
	@Scheduled(fixedDelay = 1000)
	public void printNextMessageIfTimeArrived() {
		logger.info("check if its the time to print message.. now()=" + Calendar.getInstance().getTimeInMillis());
		if(nextMessage == null) {
			logger.trace("no messages to print");
			return;
		}
		
		String[] strArr = nextMessage.split(":");
		long time = Long.parseLong(strArr[0]);
		if(time < Calendar.getInstance().getTimeInMillis()) {
			logger.info("time to print message" + strArr[1] + " is arrived ,with message "  + strArr[0] );
			printMessage(nextMessage);
			removeMessage(nextMessage);
			readNextMessage();
		}
	}
 
}
