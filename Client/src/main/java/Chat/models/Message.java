package Chat.models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Message {
    long messageStartFlag;
    long checksum;
    long operationInformation;
    long transactionHeader;
    long conversationHeader;
    long messageHeader;
    long messageLength;
}
