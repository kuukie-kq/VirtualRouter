package com.javaweb.router.bean;

public class HostRelationship {
    private int hostShipId;
    private int hostId;
    private int routerId;

    public int getHostShipId() {
        return hostShipId;
    }

    public void setHostShipId(int hostShipId) {
        this.hostShipId = hostShipId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public int getRouterId() {
        return routerId;
    }

    public void setRouterId(int routerId) {
        this.routerId = routerId;
    }
}
