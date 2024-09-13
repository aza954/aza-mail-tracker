package ru.aza954.worker.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE,makeFinal = true)
@Component
public class EmailClientApi {
    @SneakyThrows
    public void sendEmail(String destinationEmail, String message){
        Thread.sleep(1000L);
        log.info("Email to " + destinationEmail + " successfully sent");
    }
}
