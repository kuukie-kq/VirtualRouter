package main.srcd.southbound.router;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class RouterChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        /**
         *
         */
//        socketChannel.pipeline().addLast(new MyOutHandler());
//        socketChannel.pipeline().addLast(new MyWebOutHandler());
//        socketChannel.pipeline().addLast(new MySocketServerHandler());
//        socketChannel.pipeline().addLast(new MyWebSocketServerHandler());

        socketChannel.pipeline().addLast(new TestTwo());
    }
}
