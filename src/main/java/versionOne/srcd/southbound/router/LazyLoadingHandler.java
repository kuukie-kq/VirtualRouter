package versionOne.srcd.southbound.router;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

public class LazyLoadingHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new LazyLoadingLookingAll());
        socketChannel.pipeline().addLast(new EndOfCycleAndForwarding());
    }
}
