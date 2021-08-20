package versionOne.srcd.bean;

public class Router {
    private int routerId;
    private String routerName;
    private String routerAddress;

    public int getRouterId() {
        return routerId;
    }

    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }

    public String getRouterName() {
        return routerName;
    }

    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public String getRouterAddress() {
        return routerAddress;
    }

    public void setRouterAddress(String routerAddress) {
        if (routerAddress.split(":").length != 2) {
            System.err.println(routerAddress + " is not a valid url address.");
            System.exit(-101);
        }
        this.routerAddress = routerAddress;
    }
}
