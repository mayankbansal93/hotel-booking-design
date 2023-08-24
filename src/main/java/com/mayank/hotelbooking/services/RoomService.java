package com.mayank.hotelbooking.services;

import com.mayank.hotelbooking.datastore.RoomData;
import com.mayank.hotelbooking.exceptions.HotelBookingException;
import com.mayank.hotelbooking.model.Room;
import com.mayank.hotelbooking.model.RoomInventory;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

import static com.mayank.hotelbooking.exceptions.ExceptionType.*;

@Service
public class RoomService {
    private RoomData roomData;

    @Autowired
    public RoomService(RoomData roomData) {
        this.roomData = roomData;
    }

    public void addRoom(@NonNull final Room room) {
        if (roomData.getRoomById().containsKey(room.getId())) {
            throw new HotelBookingException(ROOM_ALREADY_EXISTS, "Duplicate room");
        }
        roomData.getRoomById().put(room.getId(), room);
    }

    public void addRoomInventory(@NonNull final RoomInventory roomInventory) {
        if (roomData.getRoomInventoryById().containsKey(roomInventory.getId())) {
            throw new HotelBookingException(ROOM_INVENTORY_ALREADY_EXISTS, "Duplicate room inventory");
        }
        roomData.getRoomInventoryById().put(roomInventory.getId(), roomInventory);

        Map<Timestamp, List<RoomInventory>> roomInventoriesMap = roomData.getRoomInventoriesMap().getOrDefault(roomInventory.getHotelId(), new HashMap<>());
        List<RoomInventory> roomInventories = roomInventoriesMap.getOrDefault(roomInventory.getDate(), new ArrayList<>());
        roomInventories.add(roomInventory);
        roomInventoriesMap.put(roomInventory.getDate(), roomInventories);
        roomData.getRoomInventoriesMap().put(roomInventory.getHotelId(), roomInventoriesMap);
    }

    public Room getRoom(@NonNull final String roomId) {
        return roomData.getRoomById().get(roomId);
    }

    public List<RoomInventory> getAllRoomInventory(@NonNull final String hotelId, @NonNull final Timestamp date) {
        return roomData.getRoomInventoriesMap().get(hotelId).get(date);
    }

    public void updateRoom(@NonNull final Room room) {
        if (!roomData.getRoomById().containsKey(room.getId())) {
            throw new HotelBookingException(ROOM_NOT_FOUND, "room not found");
        }
        roomData.getRoomById().put(room.getId(), room);
    }

    public void deleteRoom(@NonNull final String roomId) {
        if (!roomData.getRoomById().containsKey(roomId)) {
            throw new HotelBookingException(ROOM_NOT_FOUND, "room not found");
        }
        roomData.getRoomById().remove(roomId);
    }
}
