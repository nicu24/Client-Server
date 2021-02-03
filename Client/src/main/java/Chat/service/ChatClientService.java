package Chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ChatClientService {

    @Autowired
    ChatClient chatClient = new ChatClient();

    @PostConstruct
    public void init() {
        chatClient.connect();
    }


    public void sentMessage(String str) {
        if (verifyState()) {
            this.chatClient.sent(str);
        } else {
            System.out.println("State provide that channel is down");
            this.reconnectToServer();
        }
    }

    public void reconnectToServer() {

        while (!this.chatClient.connect()) {
            try {
                this.chatClient.connect();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public boolean verifyState() {
        System.out.println("Verify state: " + this.chatClient.state());
        return this.chatClient.state();
    }

}
