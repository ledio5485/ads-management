package de.admanagement;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;


@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"de.admanagement"})
public class AppConfig {

    @Bean
    public LocaleProvider localeProvider() {
        return LocaleContextHolder::getLocale;
    }

}
