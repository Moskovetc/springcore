package org.shop.configuration;

import org.shop.*;
import org.shop.api.ProductService;
import org.shop.api.UserService;
import org.shop.common.Sellers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataInitializerConfiguration {
    private static final long FIRST_SELLER_ID = 1;
    private static final long SECOND_SELLER_ID = 2;


    @Bean(initMethod = "initData")
    public DataInitializer dataInitializer() {
        return new DataInitializer();
    }

    @Bean
    public Map<Long, String> sellerNames() {
        Map<Long, String> sellerNames = new HashMap<>();
        sellerNames.put(FIRST_SELLER_ID, Sellers.AMAZON);
        sellerNames.put(SECOND_SELLER_ID, Sellers.SAMSUNG);
        return sellerNames;
    }

    @Bean
    public SellerInitializer sellerInitializer() {
        return new SellerInitializer();
    }

    @Bean("proposalInitializer")
    public ProposalInitializer proposalInitializer() {
        return new ProposalInitializer();
    }

    @Bean
    public UserInitializer userInitializer(UserService userService) {
        return new UserInitializer(userService);
    }

    @Bean
    public ProductInitializer productInitializer(ProductService productService) {
        return new ProductInitializer(productService);
    }

}
