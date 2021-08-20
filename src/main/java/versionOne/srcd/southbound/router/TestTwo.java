package versionOne.srcd.southbound.router;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import versionOne.srcd.bean.Host;
import versionOne.srcd.bean.Router;
import versionOne.srcd.bean.RouterTable;
import versionOne.srcd.util.RouterUtils;

import java.nio.charset.Charset;
import java.util.List;

public class TestTwo extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("<TestTwo><read>()=>");
        ByteBuf byteBuf = (ByteBuf) msg;
        String request = byteBuf.toString(RouterUtils.charset);
        System.out.println(request);

        AttributeKey<Object> attributeKey = AttributeKey.valueOf("routerId");
        Attribute<Object> attribute = ctx.channel().attr(attributeKey);
        Object attr = attribute.get();
        System.out.println(Integer.parseInt(attr.toString()));

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
                                    System.out.println("<Forward:>Active");
                                    ctx.channel().writeAndFlush(ctx.alloc().buffer().writeBytes(request.getBytes(RouterUtils.charset)));
//                                    System.out.println(request);
                                }
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf byteBuf = (ByteBuf) msg;
                                    System.out.println("<Forward:>Read");
                                    System.out.println(byteBuf.toString(RouterUtils.charset));
                                }
                            });
                        }
                    });

                    RouterTable routerTable = RouterUtils.routerTableDao.lookupRouterTableByIdAndName(Integer.parseInt(attr.toString()),vrsat[1]);
                    if(routerTable.getNextAddressName() == null) {
                        System.out.println("There are not have gold");

                        //lan jia zai
                        String ljzNext = vrsat[1];
                        for(Router router:RouterUtils.routerDao.lookupRouterGetRouters()) {
                            if(router.getRouterId() == Integer.parseInt(attr.toString())) {
                                continue;
                            }
                            String address = router.getRouterAddress();

                            Bootstrap strap = new Bootstrap();
                            strap.group(ctx.channel().eventLoop());
                            strap.channel(NioSocketChannel.class);
                            strap.handler(new ChannelInitializer<SocketChannel>() {
                                @Override
                                protected void initChannel(SocketChannel socketChannel) throws Exception {
                                    socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                        @Override
                                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                            System.out.println("<LanJiaZai:>Active");
                                            String ljzTable = "RouterTable:";
                                            Router router = RouterUtils.routerDao.lookupRouterById(Integer.parseInt(attr.toString()));
                                            for(RouterTable stringTable:RouterUtils.routerTableDao.lookupRouterTableById(Integer.parseInt(attr.toString()))) {
                                                ljzTable = ljzTable + stringTable.getReachableAddressName() + "-" +
                                                        (stringTable.getReachableDistance() + 1) + "-" + router.getRouterName() + "<>";
                                            }
                                            ljzTable = ljzTable + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE;
                                            ctx.channel().writeAndFlush(ctx.alloc().buffer().writeBytes(ljzTable.getBytes(RouterUtils.charset)));
                                        }

                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            ByteBuf byteBuf = (ByteBuf) msg;
                                            System.out.println("<LanJiaZai:>Read");
                                            System.out.println(byteBuf.toString(RouterUtils.charset));
                                            //cha zhao mu biao
                                            for(String table:byteBuf.toString(RouterUtils.charset).split(":")[1].trim().split("<>")) {
                                                String[] columns = table.split("-");

                                                if(columns[0].equals(ljzNext)) {
                                                    //
                                                    System.err.println("==================bug multiple forwarding ====================");
                                                    Router router = RouterUtils.routerDao.lookupRouterByName(columns[2]);
                                                    if(router.getRouterAddress() == null) {
                                                        System.err.println("Forwarding failed...");
                                                    } else {
                                                        String port = router.getRouterAddress().split(":")[1].trim();
                                                        bootstrap.connect("127.0.0.1",Integer.parseInt(port)).addListener(future -> {
                                                            if(future.isSuccess()) {
                                                                System.out.println("Connect success :)");
                                                            } else {
                                                                System.err.println("Connect error please try again");
                                                            }
                                                        });
                                                    }
                                                    break;
                                                }
                                            }
                                        }
                                    });
                                }
                            });

                            strap.connect(address.split(":")[0].trim(),Integer.parseInt(address.split(":")[1].trim())).addListener(future -> {
                                if(future.isSuccess()) {
                                    System.out.println("Connect success :)");
                                } else {
                                    System.err.println("Connect error please try again");
                                }
                            });
                        }

                    } else {
                        Router router = RouterUtils.routerDao.lookupRouterByName(routerTable.getNextAddressName());
                        if(router.getRouterAddress() == null) {
                            Host host = RouterUtils.hostDao.lookupHostByName(routerTable.getNextAddressName());
                            if(host.getHostAddress() == null) {
                                System.err.println("Router and Host table are not have address");
                            } else {
                                //
                                System.out.println("send my host");
                            }
                        } else {

                            String port = router.getRouterAddress().split(":")[1].trim();
                            bootstrap.connect("127.0.0.1",Integer.parseInt(port)).addListener(future -> {
                                if(future.isSuccess()) {
                                    System.out.println("Connect success :)");
                                } else {
                                    System.err.println("Connect error please try again");
                                }
                            });
                        }
                    }

//                    bootstrap.connect("127.0.0.1",55031).addListener(future -> {
//                        if(future.isSuccess()) {
//                            System.out.println("Connect success :)");
//                        } else {
//                            System.err.println("Connect error please try again");
//                        }
//                    });

                } else {
                    System.err.println("The VirtualRouter forwarding parameter is not (xx)->(xx)");
                    continue;
                }
            } else if(requestHeadLine.startsWith("RouterTable")) {
                flagUpdate = true;

                String routerTable = requestHeadLine.split(":")[1].trim();
                String[] routingTables = routerTable.split("<>");
                for(String routingTable:routingTables) {
                    String[] routingColumns = routingTable.split("-");
                    if(routingColumns.length == 3) {
                        RouterTable routerDatabase = RouterUtils.routerTableDao.lookupRouterTableByIdAndName(Integer.parseInt(attr.toString()),routingColumns[0]);
                        if(routerDatabase.getNextAddressName() == null) {
                            // insert table
                            RouterTable insertData = new RouterTable();
                            insertData.setTableId(-1);
                            Router router = RouterUtils.routerDao.lookupRouterById(Integer.parseInt(attr.toString()));
                            insertData.setTableName(router.getRouterName() + "To" + routingColumns[0]);
                            insertData.setRouterId(Integer.parseInt(attr.toString()));
                            insertData.setReachableAddressName(routingColumns[0]);
                            insertData.setReachableDistance(Integer.parseInt(routingColumns[1]));
                            insertData.setNextAddressName(routingColumns[2]);
                            RouterUtils.routerTableDao.lookRouterTable(insertData);
                        } else {
                            if(routerDatabase.getNextAddressName().equals(routingColumns[2])) {
                                //update distance
                                RouterUtils.routerTableDao.lookRouterTableByDistance(Integer.parseInt(routingColumns[1]),routerDatabase.getTableId());
                            } else {
                                if(routerDatabase.getReachableDistance() > Integer.parseInt(routingColumns[1])) {
                                    //update nextAddressName routingColumns[2]
                                    RouterUtils.routerTableDao.lookRouterTableByDistanceAndNext(Integer.parseInt(routingColumns[1]),routingColumns[2],routerDatabase.getTableId());
                                } else {
                                    System.out.println("Keep unchanged.");
                                }
                            }
                        }
                    } else {
                        System.out.println("This columns has not compliance with specifications." +
                                "It's " + routingTable + ".");
                        continue;
                    }
                }

                //ACK
                String message = "RouterTable:";
                Router router = RouterUtils.routerDao.lookupRouterById(Integer.parseInt(attr.toString()));
                for(RouterTable stringTable:RouterUtils.routerTableDao.lookupRouterTableById(Integer.parseInt(attr.toString()))) {
                    message = message + stringTable.getReachableAddressName() + "-" +
                            (stringTable.getReachableDistance() + 1) + "-" + router.getRouterName() + "<>";
                }
                message = message + RouterUtils.SEQUENCE + RouterUtils.SEQUENCE;
                ctx.write(ctx.alloc().buffer().writeBytes(message.getBytes(RouterUtils.charset)));
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
