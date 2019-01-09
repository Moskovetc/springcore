

import org.shop.*
import org.shop.api.ItemService
import org.shop.api.OrderService
import org.shop.api.ProductService
import org.shop.api.ProposalService
import org.shop.api.SellerService
import org.shop.api.UserService
import org.shop.repository.*
import org.shop.repository.factory.UserRepositoryFactory
import org.shop.repository.map.ItemMapRepository
import org.shop.repository.map.OrderMapRepository
import org.shop.repository.map.ProductMapRepository
import org.shop.repository.map.ProposalMapRepository
import org.shop.repository.map.SellerMapRepository
import org.springframework.core.io.ClassPathResource

import static org.shop.common.Sellers.AMAZON
import static org.shop.common.Sellers.SAMSUNG

def properties = new Properties()
properties.load(new ClassPathResource('props.properties').inputStream)

beans {
    //repository configuration
    userRepositoryFactory(UserRepositoryFactory)

    println(userRepositoryFactory)
//    println(userRepositoryFactory)
    userRepository(UserRepository) {
        userRepositoryFactory.createUserRepository()
    }
//    orderRepository(OrderRepository) {
//        OrderMapRepository orderMapRepository = new OrderMapRepository()
//        orderMapRepository.setSequence(Long.parseLong(properties.getProperty('intitialSequence')))
//    }
//    itemMapRepository(ItemMapRepository)
//    productMapRepository(ProductMapRepository)
//    proposalMapRepository(ProposalMapRepository)
//    sellerMapRepository(SellerMapRepository)
//
//    //service configuration
//    itemService(ItemService, itemMapRepository)
//    orderService(OrderService)
//    productService(ProductService, productMapRepository)
//    proposalService(ProposalService, proposalMapRepository)
//    sellerService(SellerService)
//    userService(UserService)
//
//    //dataInitializer configuration
//    dataInitializer(DataInitializer) { bean ->
//        bean.initMethod = 'initData'
//    }
//    sellerNames(Map) {
//        Map<Long, String> sellers = new HashMap<>()
//        sellers = [1: AMAZON, 2: SAMSUNG]
//    }
//    sellerInitializer(SellerInitializer)
//    //byName
//    proposalInitializer(ProposalInitializer) { bean ->
//        bean.autowire = 'proposalInitializer'
//
//    }
//    userInitializer(UserInitializer, UserService)
//    productInitializer(ProductInitializer, productService)

}

