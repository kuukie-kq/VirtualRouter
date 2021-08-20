package main.srcd.southbound.host;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import main.srcd.util.RouterUtils;

import java.nio.charset.Charset;

public class MyFirstHostChannelHandler extends ChannelInboundHandlerAdapter {
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
        System.out.println(byteBuf.toString(Charset.forName("utf-8")));
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        System.out.println("<Host:>Active:>ByteBuf");

        byte[] bytes = ("POST / HTTP/1.1" + RouterUtils.SEQUENCE + "VirtualRouter:host1->host2"
        + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE + "test123" + RouterUtils.SEQUENCE).getBytes(Charset.forName("utf-8"));
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
