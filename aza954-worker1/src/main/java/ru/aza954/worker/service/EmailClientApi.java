package ru.aza954.worker.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Random;

@Log4j2
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE,makeFinal = true)
@Component
public class EmailClientApi {

    public boolean sendEmail(String destinationEmail, String message){
        Random random = new Random();
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            return false;
        }
        return random.nextInt(11)<5;



    }
}
