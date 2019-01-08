package org.shop;


import configuration.BeansConfiguration;
import org.shop.api.*;
import org.shop.data.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    private static final Logger LOG = LoggerFactory.getLogger(ShopLauncher.class);

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        ApplicationContext context =
                new AnnotationConfigApplicationContext(BeansConfiguration.class);

        List<Seller> sellers = getSellers(context);
        List<Product> products = getProducts(context);
        List<User> users = getUsers(context);
        List<Proposal> proposals = getProposals(context, products);
        List<Order> orders = getOrders(context, users, proposals);

        LOG.info(sellers.toString());
        LOG.info(products.toString());
        LOG.info(users.toString());
        LOG.info(proposals.toString());
        LOG.info(orders.toString());

    }

    private static List<Order> getOrders(ApplicationContext context, List<User> users, List<Proposal> proposals) {
        List<Order> orders = new ArrayList<>();
        for (User user : users) {
            for (Proposal proposal : proposals) {
                context.getBean(OrderService.class).createOrder(user, proposal);
            }
        }
        users.forEach((user) -> orders.addAll(context.getBean(OrderService.class).getOrdersByUserId(user.getId())));
        return orders;
    }

    private static List<User> getUsers(ApplicationContext context) {
        return context.getBean(UserService.class).getUsers();
    }

    private static List<Product> getProducts(ApplicationContext context) {
        return context.getBean(ProductService.class).getProducts();
    }

    private static List<Seller> getSellers(ApplicationContext context) {
        return context.getBean(SellerService.class).getSellers();
    }

    private static List<Proposal> getProposals(ApplicationContext context, List<Product> products) {
        List<Proposal> proposals = new ArrayList<>();
        products.forEach((product) ->
                proposals.addAll(context.getBean(ProposalService.class).getProposalsByProductId(product.getId())));
        return proposals;
    }

}

