

import java.util.Scanner;

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

public class ChatClient {

    static final String HOST = "127.0.0.1";
    static final int PORT = 8007;
    static String clientName;

    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new StringDecoder());
                            p.addLast(new StringEncoder());

                            p.addLast(new ClientHandler());

                        }
                    });


            ChannelFuture f = b.connect(HOST, PORT).sync();

                String input = scanner.nextLine();
                Channel channel = f.sync().channel();
                channel.writeAndFlush(input);
                channel.flush();



            f.channel().closeFuture().sync();
        } finally {

            group.shutdownGracefully();
        }
    }
}
