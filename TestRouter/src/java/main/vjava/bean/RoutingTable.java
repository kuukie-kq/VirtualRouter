package main.vjava.bean;

public class RoutingTable {
    private int tableId;
    private String tableName;
    private int routingId;
    private String reachableAddress;
    private int reachableDistance;
    private String nextAddress;

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

    public int getRoutingId() {
        return routingId;
    }

    public void setRoutingId(int routingId) {
        this.routingId = routingId;
    }

    public String getReachableAddress() {
        return reachableAddress;
    }

    public void setReachableAddress(String reachableAddress) {
        this.reachableAddress = reachableAddress;
    }

    public int getReachableDistance() {
        return reachableDistance;
    }

    public void setReachableDistance(int reachableDistance) {
        this.reachableDistance = reachableDistance;
    }

    public String getNextAddress() {
        return nextAddress;
    }

    public void setNextAddress(String nextAddress) {
        this.nextAddress = nextAddress;
    }
}
