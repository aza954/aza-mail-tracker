package ru.aza954.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aza954.store.entities.SendEmailTaskEntity;

public interface SendEmailTaskRepository extends JpaRepository<SendEmailTaskEntity, Long> {
}
