package ru.aza954.worker;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import ru.aza954.worker.configs.importConfig;

@Import(importConfig.class)
@ComponentScan("ru.aza954.worker.service")
@ComponentScan("ru.aza954.worker.scheduled")
@EnableScheduling
@Configuration
public class EnableWorker {
}
