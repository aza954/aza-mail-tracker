package ru.aza954.aza_mail_tracker.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.aza954.store.EnableStore;
import ru.aza954.worker.EnableWorker;


@Import({
        EnableStore.class,
        EnableWorker.class

})
@Configuration
public class importConfig {
}
