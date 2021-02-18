package Chat;

import Chat.models.Message;
import Chat.service.ConvertService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        Message message = new Message(23l,520,666l,433l,2l,23l,423l);

        ConvertService.longToBytes(message.getOperationInformation(),message.getTransactionHeader());


    }

}
