package com.tony.Heaven_hotel.service.interfac;

import com.tony.Heaven_hotel.dto.Response;
import com.tony.Heaven_hotel.models.Booking;

public interface IBookingService {

    Response saveBooking(Long rooId, Long userId, Booking bookingRequest);
    Response findBookingByConfirmationCode(String confirmationCode);
    Response getAllBookings();
    Response cancelBooking(Long bookingId);
}
