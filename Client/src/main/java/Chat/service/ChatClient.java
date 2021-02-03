package Chat.service;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Component
public class ChatClient {

    static final String HOST = "127.0.0.1";
    static final int PORT = 8007;
    static final Bootstrap bootstrap = new Bootstrap();
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
                            p.addLast(new ClientHandler());
                        }
                    });
    }


    public void sent(String str) {

        if(this.state()) {
            try {
                Channel channel = channelFuture.sync().channel();
                channel.writeAndFlush(str);
                channel.flush();
                channelFuture.channel().closeFuture().sync();

            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }else {
            System.out.println("Message can not be sent: Chanal is offline neet reconect");
        }
    }


    public boolean connect(){
        System.out.println("Connect to server");

        try {
            this.channelFuture = bootstrap.connect(HOST, PORT).sync();
            return true;
        }
        catch (Exception e){
            System.out.println("Server is unreachable");
            return false;
        }
      }

    public boolean state(){
        return this.channelFuture.channel().isActive();
    }

}
