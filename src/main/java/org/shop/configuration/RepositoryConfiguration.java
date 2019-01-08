package org.shop.configuration;

import org.shop.repository.OrderRepository;
import org.shop.repository.UserRepository;
import org.shop.repository.factory.UserRepositoryFactory;
import org.shop.repository.map.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "props.properties")
public class RepositoryConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public UserRepository userRepository(UserRepositoryFactory userRepositoryFactory) {
        return userRepositoryFactory.createUserRepository();
    }

    @Bean
    public OrderRepository orderRepository() {
        OrderMapRepository orderMapRepository = new OrderMapRepository();
        orderMapRepository.setSequence(Long.parseLong(env.getProperty("intitialSequence")));
        return orderMapRepository;
    }

    @Bean
    public ItemMapRepository itemMapRepository() {
        return new ItemMapRepository();
    }

    @Bean
    public ProductMapRepository productMapRepository() {
        return new ProductMapRepository();
    }

    @Bean
    public ProposalMapRepository proposalMapRepository() {
        return  new ProposalMapRepository();
    }

    @Bean
    public SellerMapRepository sellerMapRepository() {
        return new SellerMapRepository();
    }
}
