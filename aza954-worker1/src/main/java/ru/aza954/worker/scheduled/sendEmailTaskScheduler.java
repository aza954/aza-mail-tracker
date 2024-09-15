package ru.aza954.worker.scheduled;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.aza954.store.dao.SendEmailTaskDao;
import ru.aza954.store.entities.SendEmailTaskEntity;
import ru.aza954.worker.service.EmailClientApi;
import ru.aza954.worker.service.RedisLock;
import ru.aza954.worker.service.RedisLockWrapper;

import java.time.Duration;
import java.time.Instant;

@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
@Component
public class sendEmailTaskScheduler {
    RedisLockWrapper redisLockWrapper;
    SendEmailTaskDao sendEmailTaskDao;
    EmailClientApi emailClientApi;

    RedisLock redisLock;

    private  static final  String SEND_EMAIL_TASK_KEY_FORMAT = "aza954:send:email:task:%s";
    @Scheduled(cron = "*/5 * * * * *")
    public void sendEmailTasks() {
        
        sendEmailTaskDao.findAllNotProcessed().forEach(sendEmailTask ->{

            String sendEmailTaskKey = getSendEmailTaskKey(sendEmailTask.getId());

            redisLockWrapper.lockAndExecuteTask(
                    sendEmailTaskKey,
                    Duration.ofSeconds(5),
                    () ->{
                        sendEmail(sendEmailTask);
                    }

            );




        });

    }

    private void sendEmail(SendEmailTaskEntity sendEmailTask) {
        boolean delivered = emailClientApi.sendEmail(sendEmailTask.getDestinationEmail(), sendEmailTask.getMessage());

        if (delivered){
            log.info("Сообщение успешно отправилось на почту " + sendEmailTask.getId());
            sendEmailTaskDao.markAsProcessed(sendEmailTask);
        }
        else {
            log.warn("Сообщение не отправилось на почту " + sendEmailTask.getId());
            sendEmailTaskDao.updateLastedTryAt(sendEmailTask);
        }
    }

    private static String getSendEmailTaskKey(Long taslId){
        return SEND_EMAIL_TASK_KEY_FORMAT.formatted(taslId);
    }

}

