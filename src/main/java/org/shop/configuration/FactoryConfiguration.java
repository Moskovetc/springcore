package org.shop.configuration;

import org.shop.repository.factory.UserRepositoryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfiguration {

    @Bean
    public UserRepositoryFactory userRepositoryFactory() {
        return new UserRepositoryFactory();
    }

}
