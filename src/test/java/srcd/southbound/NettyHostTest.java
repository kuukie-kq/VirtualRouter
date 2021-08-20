package srcd.southbound;

import versionOne.srcd.southbound.host.NettyHost;

public class NettyHostTest {
    public static void main(String[] args) {
        NettyHost nettyHost = new NettyHost();
        nettyHost.run();
    }
}
