package Chat.controler;


import Chat.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
public class ClientController {

    @Autowired
    private final ChatClientService chatClientService  = new ChatClientService();

    @RequestMapping("/sent")
    private void sent(@RequestParam(name = "str") String str){
      chatClientService.sentMessage(str);
    }
}
