package com.mayank.hotelbooking.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HotelBookingException extends RuntimeException{
    private ExceptionType exceptionType;
    private String message;
}
