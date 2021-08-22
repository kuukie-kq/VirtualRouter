package versionOne.srcd.southbound.router;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import versionOne.srcd.bean.Router;
import versionOne.srcd.util.RouterUtils;

public class NettyVirtualRouter {
    private int routerId;

    public NettyVirtualRouter(int routerId) {
        this.routerId = routerId;
    }

    public void run() {
        try {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workGroup = new NioEventLoopGroup();

            AttributeKey<Object> routerAttrKey = AttributeKey.valueOf("routerId");

            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup);
            serverBootstrap.childAttr(routerAttrKey,routerId);
            serverBootstrap.channel(NioServerSocketChannel.class);
            serverBootstrap.childHandler(new NettyVirtualRouterHandler());

            Router router = RouterUtils.routerDao.lookupRouterById(routerId);
            String port = router.getRouterAddress().split(":")[1].trim();
            Channel channel = serverBootstrap.bind(Integer.parseInt(port)).sync().channel();
            channel.closeFuture().sync();

            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
