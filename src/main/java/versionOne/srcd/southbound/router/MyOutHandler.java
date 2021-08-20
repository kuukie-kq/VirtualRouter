package versionOne.srcd.southbound.router;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import versionOne.srcd.util.RouterUtils;

import java.nio.charset.Charset;

public class MyOutHandler extends ChannelOutboundHandlerAdapter {
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("<Router:>out write ");
        byte[] bytes = ("Hello, I am the out write" + RouterUtils.SEQUENCE).getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        ctx.writeAndFlush(byteBuf);
        super.write(ctx, msg, promise);
    }
}
