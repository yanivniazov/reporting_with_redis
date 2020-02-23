package application.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import application.repositories.MessageRepository;

import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;


@SpringBootTest
public class EchoAtTimeServiceImplTest {
	
	@Autowired
	EchoAtTimeService echoAtTimeService;
	@Mock
	MessageRepository messageRepository;
	
	
	@BeforeEach
    void setMockNextMessageOutput() {
		Set<String> myset = new HashSet<String>();
		myset.add("yaniv");
		myset.add("amit");
        when(messageRepository.getAllOldMessages()).thenReturn(myset);     
    }
	
	
	@Test
	public void readNextMessgate() {
		echoAtTimeService.printOldMessages();
		verify(messageRepository,times(0)).getAllOldMessages();
	}
}
