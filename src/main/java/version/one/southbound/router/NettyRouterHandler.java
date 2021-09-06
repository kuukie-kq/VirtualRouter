package version.one.southbound.router;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import version.one.southbound.router.channel.AnalyzeCustomProtocols;
import version.one.southbound.router.channel.EndOfCycleAndForwarding;
import version.one.southbound.router.channel.LookingForEdgeGateway;

public class NettyRouterHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast(new AnalyzeCustomProtocols());
        socketChannel.pipeline().addLast(new LookingForEdgeGateway());
        socketChannel.pipeline().addLast(new EndOfCycleAndForwarding());
    }
}
