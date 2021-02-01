package server;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a server-side channel.
 */
public class ChatServerHandler extends SimpleChannelInboundHandler<String> {


    static final List<Channel> channels = new ArrayList<Channel>();


    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        System.out.println("INFO CLIENT  - " + ctx.channel());

        channels.add(ctx.channel().read());
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("Server received - " + msg);
        for (Channel c : channels) {
            c.writeAndFlush("Send To All client " + msg + '\n');
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("Closing connection for client - " + ctx);
        ctx.close();
    }
}
