package Chat.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Component;

@Component
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("Client is connected {message from client}" + ctx.channel());
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Message: " + msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Server is down i am looking for reconnect" + ctx.channel().localAddress());
    }
}
