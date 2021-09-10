package com.javaweb.router.bean;

import version.one.bean.Router;

import java.util.List;

public class RouterShip {
    private List<Router> routers;
    private List<Relationship> ships;

    public RouterShip() {

    }

    public RouterShip(List<Router> routers, List<Relationship> ships) {
        this.routers = routers;
        this.ships = ships;
    }

    @Override
    public String toString() {
        return "RouterShip{" +
                "routers=" + routers +
                ", ships=" + ships +
                '}';
    }

    public List<Router> getRouters() {
        return routers;
    }

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    public List<Relationship> getShips() {
        return ships;
    }

    public void setShips(List<Relationship> ships) {
        this.ships = ships;
    }
}
