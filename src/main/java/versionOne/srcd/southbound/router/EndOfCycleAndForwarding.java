package versionOne.srcd.southbound.router;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import versionOne.srcd.util.RouterUtils;

public class EndOfCycleAndForwarding extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("<EndOfCycleAndForwarding><channelRead()>====");
        String request = (String) msg;
        //System.out.println(request);

        if(request.split(RouterUtils.SEQUENCE+RouterUtils.SEQUENCE+RouterUtils.SEQUENCE).length == 2) {
            String virtual = request.split(RouterUtils.SEQUENCE+RouterUtils.SEQUENCE+RouterUtils.SEQUENCE)[0].trim();
            if(virtual.split(RouterUtils.CONNECT).length == 2) {
                String requestBody = request.split(RouterUtils.SEQUENCE+RouterUtils.SEQUENCE+RouterUtils.SEQUENCE)[1];
                String virtualBody = virtual.split(RouterUtils.CONNECT)[0].trim();

                ctx.close();
                AttributeKey<Object> routerAttrKey = AttributeKey.valueOf("requestBody");

                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(ctx.channel().eventLoop());
                bootstrap.attr(routerAttrKey,requestBody);
                bootstrap.channel(NioSocketChannel.class);
                bootstrap.handler(new ForwardingHandler());

                String[] port = virtualBody.split(":");
                bootstrap.connect(port[0],Integer.parseInt(port[1])).addListener(future -> {
                    if(future.isSuccess()) {
                        System.out.println("Connect success :)");
                    } else {
                        System.err.println("Connect error please try again");
                    }
                });

            } else {
                ctx.write(ctx.alloc().buffer().writeBytes(("error 500 for" + request).getBytes(RouterUtils.charset)));
                //ctx.fireChannelRead(request);
            }
        } else {
            ctx.write(ctx.alloc().buffer().writeBytes(request.getBytes(RouterUtils.charset)));
            //ctx.fireChannelRead(request);
        }
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("<EndOfCycleAndForwarding><channelReadComplete()>====");
        ctx.flush();
    }
}
