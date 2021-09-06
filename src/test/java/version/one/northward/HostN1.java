package version.one.northward;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import version.one.northward.host.NettyHost;
import version.one.util.RouterUtils;

public class HostN1 {
    public static void main(String[] args) {
        NettyHost nettyHost = new NettyHost();
        nettyHost.run();

        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workGroup = new NioEventLoopGroup();

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                            System.out.println("<AnalyzeCustomProtocols><channelRead()>====");
                            String request = ((ByteBuf) msg).toString(RouterUtils.charset);
                            System.out.println(request);
                        }

                        @Override
                        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                            System.out.println("<AnalyzeCustomProtocols><channelReadComplete()>====");
                            ctx.flush();
                        }
                    });
                }
            });

            Channel channel = serverBootstrap.bind(55131).sync().channel();
            channel.closeFuture().sync();

            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
