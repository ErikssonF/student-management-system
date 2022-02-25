package se.iths.errorMessage;

import java.time.LocalDateTime;

public class ErrorMessage {

    LocalDateTime localDateTime = LocalDateTime.now();
    String errorcode;
    String message;
    String url;

    public ErrorMessage(String errorcode, String message, String url) {
        this.errorcode = errorcode;
        this.message = message;
        this.url = url;
    }

    public LocalDateTime getLocalDateTime() {return localDateTime;}

    public String getErrorcode() {return errorcode;}

    public String getUrl() {return url;}

    public String getMessage() {return message;}
}