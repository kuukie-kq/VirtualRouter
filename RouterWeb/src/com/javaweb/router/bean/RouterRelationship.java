package com.javaweb.router.bean;

public class RouterRelationship {
    private int routerShipId;
    private int routerIdFrom;
    private int routerIdTo;

    public int getRouterShipId() {
        return routerShipId;
    }

    public void setRouterShipId(int routerShipId) {
        this.routerShipId = routerShipId;
    }

    public int getRouterIdFrom() {
        return routerIdFrom;
    }

    public void setRouterIdFrom(int routerIdFrom) {
        this.routerIdFrom = routerIdFrom;
    }

    public int getRouterIdTo() {
        return routerIdTo;
    }

    public void setRouterIdTo(int routerIdTo) {
        this.routerIdTo = routerIdTo;
    }
}
