package com.mayank.hotelbooking.services;

import com.mayank.hotelbooking.datastore.ReservationData;
import com.mayank.hotelbooking.exceptions.HotelBookingException;
import com.mayank.hotelbooking.model.Reservation;
import com.mayank.hotelbooking.model.RoomInventory;
import com.mayank.hotelbooking.model.RoomType;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mayank.hotelbooking.exceptions.ExceptionType.RESERVATION_ALREADY_EXISTS;
import static com.mayank.hotelbooking.exceptions.ExceptionType.RESERVATION_NOT_FOUND;

@Service
public class ReservationService {
    private ReservationData reservationData;
    private RoomService roomService;

    @Autowired
    public ReservationService(ReservationData reservationData, RoomService roomService) {
        this.reservationData = reservationData;
        this.roomService = roomService;
    }

    public void addReservation(@NonNull final Reservation reservation) {
        if (reservationData.getReservationById().containsKey(reservation.getId())) {
            throw new HotelBookingException(RESERVATION_ALREADY_EXISTS, "Duplicate reservation");
        }
        if (!validateReservation(reservation)) {
            reservation.markReservationRejected();
            return;
        }
        reservationData.getReservationById().put(reservation.getId(), reservation);

        List<String> reservationIds = reservationData.getReservationIdsByUserId()
                .getOrDefault(reservation.getUserId(), new ArrayList<>());
        reservationIds.add(reservation.getId());
        reservationData.getReservationIdsByUserId().put(reservation.getUserId(), reservationIds);

        List<String> reservationIdsByHotelId = reservationData.getReservationIdsByHotelId()
                .getOrDefault(reservation.getHotelId(), new ArrayList<>());
        reservationIdsByHotelId.add(reservation.getId());
        reservationData.getReservationIdsByHotelId().put(reservation.getHotelId(), reservationIdsByHotelId);

        reservation.markReservationWaitingForPayment();
    }

    public Reservation getReservation(@NonNull final String reservationId) {
        return reservationData.getReservationById().get(reservationId);
    }

    public List<Reservation> getAllReservationsByHotelId(@NonNull final String hotelId) {
        List<Reservation> al = new ArrayList<>();
        for(String reservationId : reservationData.getReservationIdsByHotelId().get(hotelId)){
            al.add(reservationData.getReservationById().get(reservationId));
        }
        return al;
    }

    public List<Reservation> getAllReservationsByUserId(@NonNull final String userId) {
        List<Reservation> al = new ArrayList<>();
        for(String reservationId : reservationData.getReservationIdsByUserId().get(userId)){
            al.add(reservationData.getReservationById().get(reservationId));
        }
        return al;
    }

    public void cancelReservation(@NonNull final String reservationId) {
        if (!reservationData.getReservationById().containsKey(reservationId)) {
            throw new HotelBookingException(RESERVATION_NOT_FOUND, "reservation not found");
        }
        String hotelId = reservationData.getReservationById().get(reservationId).getHotelId();
        String userId = reservationData.getReservationById().get(reservationId).getUserId();
        reservationData.getReservationById().remove(reservationId);
        reservationData.getReservationIdsByHotelId().get(hotelId).remove(reservationId);
        reservationData.getReservationIdsByUserId().get(userId).remove(reservationId);
    }

    private boolean validateReservation(Reservation reservation) {
        for(long t = reservation.getStartDate().getTime(); t <= reservation.getEndDate().getTime(); t++){
            List<RoomInventory> roomInventories = roomService.getAllRoomInventory(reservation.getHotelId(), new Timestamp(t));
            Map<RoomType, Integer> availableRooms = new HashMap<>();
            for(RoomInventory roomInventory : roomInventories){
                availableRooms.put(roomInventory.getRoomType(), roomInventory.getTotalInventory()-roomInventory.getReservedInventory());
            }
            Map<RoomType, Integer> bookedRooms = reservation.getRoomsBooked();
            for(Map.Entry<RoomType, Integer> me: bookedRooms.entrySet()) {
                int availableInventory = availableRooms.get(me.getKey());
                if(availableInventory < me.getValue()){
                    return false;
                }
            }
        }
        return true;
    }
}
