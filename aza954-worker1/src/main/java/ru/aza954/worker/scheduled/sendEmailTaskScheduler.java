package ru.aza954.worker.scheduled;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.aza954.store.dao.SendEmailTaskDao;
import ru.aza954.worker.service.EmailClientApi;

import java.time.Instant;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Component
public class sendEmailTaskScheduler {
    SendEmailTaskDao sendEmailTaskDao;
    EmailClientApi emailClientApi;
    @Scheduled(cron = "*/5 * * * * *")
    public void sendEmailTasks() {
        sendEmailTaskDao.findAllNotProcessed().forEach(sendEmailTask ->{
            Boolean delivered = emailClientApi.sendEmail(sendEmailTask.getDestinationEmail(),sendEmailTask.getMessage());
            if (delivered){
                log.debug("Сообщение успешно отправилось на почту " + sendEmailTask.getDestinationEmail());
                sendEmailTaskDao.markAsProcessed(sendEmailTask);
            }
            else {
                log.warn("Сообщение не отправилось на почту " + sendEmailTask.getDestinationEmail());
                sendEmailTaskDao.updateLastedTryAt(sendEmailTask);
            }
        });

    }
}

