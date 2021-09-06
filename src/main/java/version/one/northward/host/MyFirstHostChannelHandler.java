package version.one.northward.host;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import version.one.util.RouterUtils;

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
        System.out.println(byteBuf.toString(RouterUtils.charset));
    }

    public ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf byteBuf = ctx.alloc().buffer();
        System.out.println("<Host:>Active:>ByteBuf");

        byte[] bytes = ("GET / HTTP/1.1" + RouterUtils.SEQUENCE + "VirtualRouter:HostOne->www.baidu.com" +
                RouterUtils.SEQUENCE + "accept:*/*" + RouterUtils.SEQUENCE + "connection:Keep-Alive" + RouterUtils.SEQUENCE +"user-agent:Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)"
                + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE).getBytes(RouterUtils.charset);

//        byte[] bytes = ("POST / HTTP/1.1" + RouterUtils.SEQUENCE + "RouterTable:HostThree-2-HostTwo"
//        + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE).getBytes(RouterUtils.charset);
        byteBuf.writeBytes(bytes);
        return byteBuf;
    }
}
