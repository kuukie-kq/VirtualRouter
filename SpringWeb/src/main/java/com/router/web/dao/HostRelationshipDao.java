package com.router.web.dao;

import com.router.web.bean.HostRelationship;

import java.util.List;

public interface HostRelationshipDao {
    List<HostRelationship> lookupHostShipGetHostShips();
    int lookupHostShipGetHostShipsNumber();
    List<HostRelationship> lookupHostShipGetHostShipsByLimit(int page);
    HostRelationship lookupHostShipGetHostShipById(int id);
    boolean lookHostShipByRouterAndHostId(int hostId,int routerId);
}
