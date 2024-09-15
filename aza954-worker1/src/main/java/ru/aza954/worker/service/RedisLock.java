package ru.aza954.worker.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class RedisLock {

    private static final String LOCK_FORMAT = "%s::lock";
    ValueOperations<String, Long> valueOperations;

    RedisTemplate<String,Long> redisTemplate;

    public void releaseLock(String key) {
        String lockKey = getLockKey(key);
        redisTemplate.delete(lockKey);
    }

    public boolean acquireLock(String key, Duration duration) {
        String lockKey = getLockKey(key);
        Long expiresAtMillis = valueOperations.get(lockKey);
        Long currentTimeMillis = System.currentTimeMillis();

        if (Objects.nonNull(expiresAtMillis)) {
            if (currentTimeMillis <= expiresAtMillis) {
                return false;
            }
            redisTemplate.delete(lockKey);
        }

        return Optional.ofNullable(
                valueOperations.setIfAbsent(lockKey, currentTimeMillis + duration.toMillis())
        ).orElse(false);
    }

    private String getLockKey(String key) {
        return String.format(LOCK_FORMAT, key);
    }
}