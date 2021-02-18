package Chat.controler;


import Chat.service.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/data")
@AllArgsConstructor
public class ClientController {
    private final ChatClientService chatClientService;

    @RequestMapping(value="/sent",  method = RequestMethod.GET, produces = "application/json")
    private String sent(@RequestParam(name = "str") String str){
        chatClientService.sentMessage(str);

      return "200";
    }
}
