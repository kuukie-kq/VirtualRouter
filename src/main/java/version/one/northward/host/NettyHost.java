package version.one.northward.host;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyHost {
    public void run() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup);
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.handler(new HostChildChannelHandler());

        bootstrap.connect("127.0.0.1",55031).addListener(future -> {
            if(future.isSuccess()) {
                System.out.println("Connect success :)");
            } else {
                System.err.println("Connect error please try again");
            }
        });
    }
}
