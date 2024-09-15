package ru.aza954.aza_mail_tracker.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import ru.aza954.store.dao.SendEmailTaskDao;
import ru.aza954.store.entities.SendEmailTaskEntity;


@RestController
@RequiredArgsConstructor
public class EmailController {
    @Autowired


    public static final String SEND_EMAIL = "/api/email/send";
    private final SendEmailTaskDao sendEmailTaskDao;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping(SEND_EMAIL)
    public void sendEmail(
            @RequestParam("destination_email") String destinationEmail,
            @RequestParam String message){
        sendEmailTaskDao.save(SendEmailTaskEntity
                .builder()
                .destinationEmail(destinationEmail)
                .message(message)
                .build());



    }
}
