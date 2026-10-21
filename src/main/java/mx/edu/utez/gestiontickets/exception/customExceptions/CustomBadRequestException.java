package mx.edu.utez.gestiontickets.exception.customExceptions;

public class CustomBadRequestException extends RuntimeException {

    public CustomBadRequestException(String message) {
        super(message);
    }
}
