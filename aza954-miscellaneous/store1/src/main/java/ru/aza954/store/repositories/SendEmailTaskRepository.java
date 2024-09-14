package ru.aza954.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.aza954.store.entities.SendEmailTaskEntity;

import java.util.List;

public interface SendEmailTaskRepository extends JpaRepository<SendEmailTaskEntity, Long> {
    @Query("SELECT task FROM SendEmailTaskEntity task WHERE task.processedAt IS NULL ORDER BY task.createdAt")
    List<SendEmailTaskEntity> findAllNotProcessed();


    @Modifying
    @Query("UPDATE SendEmailTaskEntity task SET task.processedAt = CURRENT_TIMESTAMP WHERE task.id= :id")
    void markAsProcessed(@Param("id") Long id);


    @Modifying
    @Query("UPDATE SendEmailTaskEntity task SET task.lastedTryAt = CURRENT_TIMESTAMP WHERE task.id = :id")
    void updateLastedTryAt(@Param("id") Long id);
}
