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


    public void sentMessage(String str){

        this.chatClient.sent(str);
    }

    public void reconnectToServer(){

    }

}
