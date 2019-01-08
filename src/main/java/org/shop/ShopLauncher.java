package org.shop;


import org.shop.api.*;
import org.shop.configuration.ApplicationConfiguration;
import org.shop.data.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * The ShopLauncher class.
 */
public class ShopLauncher {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

        List<Seller> sellers = getSellers(context);
        List<Product> products = getProducts(context);
        List<User> users = getUsers(context);
        List<Proposal> proposals = getProposals(context, products);
        List<Order> orders = getOrders(context, users, proposals);
        System.out.println(sellers.toString());
        System.out.println(products.toString());
        System.out.println(users.toString());
        System.out.println(proposals.toString());
        System.out.println(orders.toString());

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

