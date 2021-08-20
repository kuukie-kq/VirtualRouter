package main.srcd.southbound.router;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import main.srcd.util.RouterUtils;

import java.nio.charset.Charset;

public class TestTwo extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("<TestTwo><read>()=>");
        ByteBuf byteBuf = (ByteBuf) msg;
        String request = byteBuf.toString(Charset.forName("utf-8"));
        System.out.println(request);

        String requestHead = request.split(RouterUtils.SEQUENCE+RouterUtils.SEQUENCE)[0];
        String[] requestHeadLines = requestHead.split(RouterUtils.SEQUENCE);
        boolean flagForward = false;
        boolean flagUpdate = false;
        for(String requestHeadLine:requestHeadLines) {
            if(requestHeadLine.startsWith("VirtualRouter")) {
                String virtualRouter = requestHeadLine.split(":")[1].trim();
                String[] vrsat = virtualRouter.split("->");
                if(vrsat.length == 2) {
                    flagForward = true;

                    ctx.close();
                    Bootstrap bootstrap = new Bootstrap();
                    bootstrap.group(ctx.channel().eventLoop());
                    bootstrap.channel(NioSocketChannel.class);
                    bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                    System.out.println("<Host:>Active");
                                    ctx.channel().writeAndFlush(getByteBuf(ctx,request));
//                                    System.out.println(request);
                                }
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf byteBuf = (ByteBuf) msg;
                                    System.out.println("<Host:>Read");
                                    System.out.println(byteBuf.toString(Charset.forName("utf-8")));
                                }

                                public ByteBuf getByteBuf(ChannelHandlerContext ctx,String request) {
                                    ByteBuf byteBuf = ctx.alloc().buffer();
                                    System.out.println("<Host:>Active:>ByteBuf");

                                    byte[] bytes = request.getBytes(Charset.forName("utf-8"));
                                    byteBuf.writeBytes(bytes);
                                    return byteBuf;
                                }
                            });
                        }
                    });
                    bootstrap.connect("127.0.0.1",55031).addListener(future -> {
                        if(future.isSuccess()) {
                            System.out.println("Connect success :)");
                        } else {
                            System.err.println("Connect error please try again");
                        }
                    });

                } else {
                    System.err.println("The VirtualRouter forwarding parameter is not (xx)->(xx)");
                    continue;
                }
            } else if(requestHeadLine.startsWith("RouterTable")) {
                flagUpdate = true;
            } else {
                continue;
            }
        }


    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("<TestTwo><complete>()");
        ctx.flush();
    }
}
