package ru.aza954.store.dao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aza954.store.entities.SendEmailTaskEntity;
import ru.aza954.store.repositories.SendEmailTaskRepository;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class SendEmailTaskDao {
    SendEmailTaskRepository sendEmailTaskRepository;
    @Transactional
    public SendEmailTaskEntity save(SendEmailTaskEntity entity){
        return sendEmailTaskRepository.save(entity);
    }
}
