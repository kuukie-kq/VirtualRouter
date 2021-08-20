package versionOne.srcd.southbound.router;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;

public class MyWebSocketServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf request = (ByteBuf) msg;
        System.out.println("<Router:>Read >> " + request.toString(Charset.forName("utf-8")));

        ByteBuf response = getByteBuf(ctx);
        ctx.write(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("<Router:>read complete");
        ctx.flush();
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx) throws Exception {
        byte[] bytes = ("Hello, I am the Router").getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
