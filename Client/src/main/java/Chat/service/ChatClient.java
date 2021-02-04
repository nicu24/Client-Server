package Chat.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatClient {

    static final String HOST = "127.0.0.1";
    static final int PORT = 8007;
    static final Bootstrap bootstrap = new Bootstrap();

    private final ClientHandler clientHandler;
    private ChannelFuture channelFuture;

    @PostConstruct
    public void init() {
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    public void initChannel(SocketChannel ch) {

                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new StringDecoder());
                        p.addLast(new StringEncoder());
                        p.addLast(clientHandler);
                    }
                });
    }

    public boolean connect() {
        log.info("Connect to server");
        try {
            this.channelFuture = bootstrap.connect(HOST, PORT).sync();
            return true;
        } catch (Exception e) {
            log.error("Server is unreachable", e);
            System.out.println("Server is unreachable");
            return false;
        }
    }


    public void sent(String str) {
        try {
            Channel channel = channelFuture.sync().channel();
            channel.writeAndFlush(str);
            channel.flush();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean state() {
        return this.channelFuture.channel().isActive();
    }

}
