package srcd.util;

import versionOne.srcd.bean.Host;
import versionOne.srcd.bean.Router;
import versionOne.srcd.bean.RouterTable;
import versionOne.srcd.util.RouterUtils;

public class MysqlTest {
    public static void main(String[] args) {
//        for(Host host:RouterUtils.hostDao.lookupHostGetHosts()) {
//            System.out.print(" id:"+host.getHostId());
//            System.out.print(" name:"+host.getHostName());
//            System.out.println(" address:"+host.getHostAddress());
//        }

//        for(Router router:RouterUtils.routerDao.lookupRouterGetRouters()) {
//            System.out.print(" id:"+router.getRouterId());
//            System.out.print(" name:"+router.getRouterName());
//            System.out.println(" address:"+router.getRouterAddress());
//        }

        for(RouterTable routerTable:RouterUtils.routerTableDao.lookupRouterTableGetRouterTables()) {
            System.out.print(" id:"+routerTable.getTableId());
            System.out.print(" name:"+routerTable.getTableName());
            System.out.print(" router:"+routerTable.getRouterId());
            System.out.print(" reachable:"+routerTable.getReachableAddressName());
            System.out.print(" distance:"+routerTable.getReachableDistance());
            System.out.println(" next:"+routerTable.getNextAddressName());
        }

        RouterTable routerTable = new RouterTable();
        routerTable.setTableId(17);
        routerTable.setTableName("test");
        routerTable.setRouterId(5);
        routerTable.setReachableAddressName("testSource");
        routerTable.setReachableDistance(100);
        routerTable.setNextAddressName("testNext");
        RouterUtils.routerTableDao.lookRouterTable(routerTable);
    }
}
