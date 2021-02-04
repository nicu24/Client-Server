package Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ChatClientService {

    @Autowired
    ChatClient chatClient;


    @PostConstruct
    public void init() {
        this.connectToServer();
    }

    public void connectToServer() {
                this.chatClient.connect();
    }

    public void sentMessage(String str) {
        if (verifyState()) {
            this.chatClient.sent(str);
        } else {
            System.out.println("State provide that channel is down");
        }

    }


    public boolean verifyState() {
        System.out.println("Verify state: " + this.chatClient.state());
        return this.chatClient.state();
    }

}
