package ru.aza954.aza_mail_tracker.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aza954.aza_mail_tracker.store.entities.SendEmailTaskEntity;

public interface SendEmailTaskRepository extends JpaRepository<SendEmailTaskEntity, Long> {
}
