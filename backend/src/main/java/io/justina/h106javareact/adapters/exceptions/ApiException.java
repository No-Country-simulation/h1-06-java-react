package io.justina.h106javareact.adapters.exceptions;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class ApiException {
    private List<String> errorMessages;
    private ZonedDateTime timestamp;

    public ApiException(List<String> errorMessages) {
        this.errorMessages = errorMessages;
        this.timestamp =  ZonedDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires"));
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }
    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}
