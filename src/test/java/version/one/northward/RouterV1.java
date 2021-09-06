package version.one.northward;

import version.one.southbound.router.NettyRouter;

public class RouterV1 {
    public static void main(String[] args) {
        NettyRouter nettyRouter = new NettyRouter();
        nettyRouter.run();
    }
}
