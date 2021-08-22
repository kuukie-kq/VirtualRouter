package versionOne.srcd.util;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import versionOne.srcd.dao.HostDao;
import versionOne.srcd.dao.RouterDao;
import versionOne.srcd.dao.RouterTableDao;

import java.nio.charset.Charset;

public class RouterUtils {
    public static final String VR = "VirtualRouter";
    public static final String RT = "RouterTable";
    public static Charset charset = Charset.forName("utf-8");
    public static final String SEQUENCE = "\r\n";
    public static final String FORWARD = "<Forward>";
    public static final String UPDATE = "<Update>";
    public static final String CONNECT = "<Connect>";
    public static final String LAZE = "<Laze>";
    public static HostDao hostDao = new HostDao();
    public static RouterDao routerDao = new RouterDao();
    public static RouterTableDao routerTableDao = new RouterTableDao();
}
