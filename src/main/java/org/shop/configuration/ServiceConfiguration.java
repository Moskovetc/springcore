package org.shop.configuration;

import org.shop.api.*;
import org.shop.api.impl.*;
import org.shop.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Bean
    public ItemService itemService(ItemRepository repository) {
        return new ItemServiceImpl(repository);
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }

    @Bean
    public ProductService productService(ProductRepository repository) {
        return new ProductServiceImpl(repository);
    }

    @Bean
    public ProposalService proposalService(ProposalRepository repository) {
        return new ProposalServiceImpl(repository);
    }

    @Bean
    public SellerService sellerService() {
        return new SellerServiceImpl();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

}
