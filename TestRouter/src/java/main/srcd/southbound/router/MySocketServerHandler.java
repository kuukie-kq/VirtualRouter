package main.srcd.southbound.router;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import main.srcd.util.RouterUtils;

import java.nio.charset.Charset;

public class MySocketServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf request = (ByteBuf) msg;
        System.out.println("<Router:>socket read >> " + request.toString(Charset.forName("utf-8")));

        ByteBuf response = getByteBuf(ctx);
//        ctx.write(request);
        ctx.fireChannelRead(response);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("<Router:>socket read complete");
        ctx.flush();
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx) throws Exception {
        byte[] bytes = ("Hi world" + RouterUtils.SEQUENCE).getBytes(Charset.forName("utf-8"));
        ByteBuf byteBuf = ctx.alloc().buffer();
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
