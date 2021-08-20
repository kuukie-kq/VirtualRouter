package main.srcd.southbound.host;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class HostChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new MyFirstHostChannelHandler());
    }
}
