package ru.aza954.store;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("ru.aza954.store.dao")
@EntityScan("ru.aza954.store.entities")
@EnableJpaRepositories("ru.aza954.store.repositories")
public class EnableStore {
}
