package me.mahfud.example.exception;

public class BusNotFoundException extends RuntimeException {

    private Long busId;

    public BusNotFoundException(String message, Long busId) {
        super(message);
        this.busId = busId;
    }

}
