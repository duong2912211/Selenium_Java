package helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Timer {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return now.format(formatter);
    }

    public boolean isInNext5Minutes(String dateTime) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeToCheck = LocalDateTime.parse(dateTime, formatter);
        LocalDateTime fiveMinutesLater = now.plusMinutes(5);

        // Time must be after now and before now + 5 minutes
        return !timeToCheck.isBefore(now) && !timeToCheck.isAfter(fiveMinutesLater);
    }
}
