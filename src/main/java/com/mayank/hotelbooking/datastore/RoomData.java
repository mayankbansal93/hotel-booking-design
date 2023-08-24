package com.mayank.hotelbooking.datastore;

import com.mayank.hotelbooking.model.Room;
import com.mayank.hotelbooking.model.RoomInventory;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Getter
public class RoomData {
    private Map<String, Room> roomById = new HashMap<>();
    private Map<String, RoomInventory> roomInventoryById = new HashMap<>();
    private Map<String, Map<Timestamp, List<RoomInventory>>> roomInventoriesMap = new HashMap<>();
}
