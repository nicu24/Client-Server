package server;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles a server-side channel.
 */
@Slf4j
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {


    static final List<Channel> channels = new ArrayList<Channel>();


    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("INFO CLIENT  - " + ctx.channel());

        channels.add(ctx.channel().read());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg)  {
        System.out.println("Server received - " + msg);

        char[] chars = new char[msg.length()];
        char swap;
        int length = msg.length()-1;
        String reverse;

        for (int i = 0; i < msg.length(); i++) {
            chars[i] = msg.charAt(i);
        }

        for (int i = 0; i < msg.length()/2; i++) {
            swap = chars[i];
            chars[i] = chars[length-i];
            chars[length-i] = swap;
        }

        reverse = String.valueOf(chars);
        log.info(reverse);

        for (Channel c : channels) {
            c.writeAndFlush("Send To All client " + reverse + '\n');
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Closing connection for client - " + ctx);
        ctx.close();
    }
}
