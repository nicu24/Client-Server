package Chat.service;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class ChatClientServiceImpl extends SimpleChannelInboundHandler<String> implements  ChatClientService{
    private final ChatClient chatClient;

    @EventListener
    private void init() {
        this.chatClient.init(this);
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


    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("Client is connected {message from client}" + ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        System.out.println("Message: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Server is down i am looking for reconnect" + ctx.channel().localAddress());
        this.chatClient.connect();
    }

}
