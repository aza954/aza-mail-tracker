package ru.aza954.worker.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.aza954.store.EnableStore;

@Import({
        EnableStore.class
})
@EnableScheduling
@Configuration
public class importConfig {
}
