package com.trackAPI.exceptions;

public class TrackApiExceptions extends RuntimeException {
    public TrackApiExceptions(String message, Throwable cause){
        super(message,cause);
    }
}
