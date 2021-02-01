package Chat.controler;


import Chat.data.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class ClientController {
    private ChatClient chatClient;

    @RequestMapping("/run")
    private void runClient(){
        if (chatClient == null)
        {
            this.chatClient = new ChatClient();
            chatClient.start();
        } else{
            System.out.println("Chat client is already created");
        }
    }
}
