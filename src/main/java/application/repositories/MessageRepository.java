package application.repositories;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author yaniv.n
 * this repository handles Redis access for managing echo messages
 */
@Repository
public class MessageRepository{
	
	final static String messageSortedSet = "ECHO_AT_TIME_ZSET";
	final static long startIndex = 0;
	final static long nextMessageIndex = 1;
	final static long endIndex = -1;
	final static double minScore = 0;
	@Autowired
	RedisTemplate<String, String> redisTemplate;
	
	public void addMessage(long time, String message) {
		redisTemplate.opsForZSet().add(messageSortedSet, time + ":" + message, time);
	}
	
	
	public void removeMessage(String time, String message) {
		redisTemplate.opsForZSet().remove(messageSortedSet, time + ":" + message);
	}
	
	public String getNextMessage() {
		Set<String> setOfMessages = redisTemplate.opsForZSet().range(messageSortedSet, startIndex, nextMessageIndex);
		if(setOfMessages.isEmpty()) {
			return null;
		}
		else
		{
			 Iterator<String> iter = setOfMessages.iterator();
			 return iter.next();
		}
	}
	
	public Set<String> getAllOldMessages() {
		long currentTime = Calendar.getInstance().getTimeInMillis();
		Double currnetTimeDouble = new Double(currentTime);
		return redisTemplate.opsForZSet().rangeByScore(messageSortedSet, minScore, currnetTimeDouble);
	}
	

}
