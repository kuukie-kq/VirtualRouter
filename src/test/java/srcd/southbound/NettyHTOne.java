package srcd.southbound;

import versionOne.srcd.southbound.host.NettyHost;

public class NettyHTOne {
    public static void main(String[] args) {
        NettyHost nettyHost = new NettyHost();
        nettyHost.run();
    }
}
