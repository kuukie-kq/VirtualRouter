package version.one.util;

import version.one.bean.Host;
import version.one.bean.Router;
import version.one.northward.router.NettyVirtualRouter;
import version.one.southbound.router.NettyRouter;

import java.util.List;

public class RouterWebUtils {
    public static void southboundRouter() {
        NettyRouter nettyRouter = new NettyRouter();
        nettyRouter.run();
    }
    public static void northwardRouter() {
        for(Router router:RouterUtils.routerDao.lookupRouterGetRouters()) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    NettyVirtualRouter nettyVR = new NettyVirtualRouter(router.getRouterId());
                    nettyVR.run();
                }
            }).start();
        }
    }
    public static void northwardHost() {
        for(Host host:RouterUtils.hostDao.lookupHostGetHosts()) {

        }
    }
}
