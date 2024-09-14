package ru.aza954.store.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aza954.store.entities.SendEmailTaskEntity;
import ru.aza954.store.repositories.SendEmailTaskRepository;

import java.util.List;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class SendEmailTaskDao {
    SendEmailTaskRepository sendEmailTaskRepository;
    @Transactional
    public SendEmailTaskEntity save(SendEmailTaskEntity entity){
        return sendEmailTaskRepository.save(entity);
    }
    public List<SendEmailTaskEntity> findAllNotProcessed(){
        return sendEmailTaskRepository.findAllNotProcessed();

    }
    @Transactional
    public void markAsProcessed(SendEmailTaskEntity sendEmailTaskEntity){
        sendEmailTaskRepository.markAsProcessed(sendEmailTaskEntity.getId());
    }
    @Transactional
    public void updateLastedTryAt(SendEmailTaskEntity sendEmailTaskEntity) {
        sendEmailTaskRepository.updateLastedTryAt(sendEmailTaskEntity.getId());
    }
}
