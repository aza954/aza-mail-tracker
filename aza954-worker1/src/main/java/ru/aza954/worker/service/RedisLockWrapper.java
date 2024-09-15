package ru.aza954.worker.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.time.Duration;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = lombok.AccessLevel.PRIVATE)
@Component
public class RedisLockWrapper {
    RedisLock redisLock;
    public  void lockAndExecuteTask(String key, Duration duration, Runnable runnable){
        try {
            if (!redisLock.acquireLock(key,duration)){
                return;
            }
            runnable.run();
        }finally {
            redisLock.releaseLock(key);
        }
    }
}
