package main.srcd.bean;

public class RouterTable {
    private int tableId;
    private String tableName;
    private int routerId;
    private String reachableAddressName;
    private int reachableDistance;
    private String nextAddressName;

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getRouterId() {
        return routerId;
    }

    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }

    public String getReachableAddressName() {
        return reachableAddressName;
    }

    public void setReachableAddressName(String reachableAddressName) {
        this.reachableAddressName = reachableAddressName;
    }

    public int getReachableDistance() {
        return reachableDistance;
    }

    public void setReachableDistance(int reachableDistance) {
        this.reachableDistance = reachableDistance;
    }

    public String getNextAddressName() {
        return nextAddressName;
    }

    public void setNextAddressName(String nextAddressName) {
        this.nextAddressName = nextAddressName;
    }
}
