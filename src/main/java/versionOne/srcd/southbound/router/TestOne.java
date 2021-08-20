package versionOne.srcd.southbound.router;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class TestOne extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf request = (ByteBuf) msg;
        System.out.println("<TestOne><read>()=>");
        System.out.println(request.toString(Charset.forName("utf-8")));

        ctx.write(request);
        ctx.fireChannelRead(request);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("<TestOne><complete>()");
        ctx.flush();
    }
}
