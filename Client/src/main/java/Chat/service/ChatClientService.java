package Chat.service;

public interface ChatClientService {
    void sentMessage(String str);

    boolean verifyState();

}
