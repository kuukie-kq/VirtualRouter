package com.javaweb.router.server;

import com.javaweb.router.util.RouterWebUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import version.one.util.RouterUtils;

public class RequestAndResponse {
    public void send(String head,String body) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelActive(ChannelHandlerContext ctx) throws Exception {
                        System.out.println("<Host:>Active");

                        ByteBuf byteBuf = getByteBuf(ctx);

                        ctx.channel().writeAndFlush(byteBuf);
                    }

                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        System.out.println("<Host:>Read");
                        System.out.println(byteBuf.toString(RouterUtils.charset));
                    }

                    public ByteBuf getByteBuf(ChannelHandlerContext ctx) {
                        ByteBuf byteBuf = ctx.alloc().buffer();
                        System.out.println("<Host:>Active:>ByteBuf");

                        byte[] bytes = ("POST / HTTP/1.1" + RouterUtils.SEQUENCE + "VirtualRouter:" + head +
                                RouterUtils.SEQUENCE + "accept:*/*" + RouterUtils.SEQUENCE + "connection:Keep-Alive" + RouterUtils.SEQUENCE +"user-agent:Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"
                                + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE + body + RouterUtils.SEQUENCE).getBytes(RouterUtils.charset);

                        byteBuf.writeBytes(bytes);
                        return byteBuf;
                    }
                });
            }
        });

        int hostId = RouterUtils.hostDao.lookupHostByName(head.split("->")[0]).getHostId();
        int routerId = RouterWebUtils.hostRelationshipDao.lookupHostShipGetHostShipById(hostId).getRouterId();
        String[] addresses = RouterUtils.routerDao.lookupRouterById(routerId).getRouterAddress().split(":");
        String host = addresses[0];
        int port = Integer.parseInt(addresses[1]);
        bootstrap.connect(host,port).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("Connect success :)");
            } else {
                System.err.println("Connect error please try again");
            }
        });
    }
}
