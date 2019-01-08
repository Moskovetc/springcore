package configuration

import org.shop.DataInitializer
import org.shop.ProductInitializer
import org.shop.ProposalInitializer
import org.shop.SellerInitializer
import org.shop.UserInitializer
import org.shop.api.ItemService
import org.shop.api.OrderService
import org.shop.api.ProductService
import org.shop.api.ProposalService
import org.shop.api.SellerService
import org.shop.api.UserService
import org.shop.api.impl.ItemServiceImpl
import org.shop.api.impl.OrderServiceImpl
import org.shop.api.impl.ProductServiceImpl
import org.shop.api.impl.ProposalServiceImpl
import org.shop.api.impl.SellerServiceImpl
import org.shop.api.impl.UserServiceImpl
import org.shop.common.Sellers
import org.shop.repository.ItemRepository
import org.shop.repository.OrderRepository
import org.shop.repository.ProductRepository
import org.shop.repository.ProposalRepository
import org.shop.repository.UserRepository
import org.shop.repository.factory.UserRepositoryFactory
import org.shop.repository.map.ItemMapRepository
import org.shop.repository.map.OrderMapRepository
import org.shop.repository.map.ProductMapRepository
import org.shop.repository.map.ProposalMapRepository
import org.shop.repository.map.SellerMapRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment

@Configuration
@EnableAspectJAutoProxy
@PropertySource(value = "props.properties")
class BeansConfiguration {
    private static final long FIRST_SELLER_ID = 1
    private static final long SECOND_SELLER_ID = 2


    @Bean(initMethod = "initData")
    DataInitializer dataInitializer() {
        new DataInitializer()
    }

    @Bean
    Map<Long, String> sellerNames() {
        Map<Long, String> sellerNames = new HashMap<>()
        sellerNames.put(FIRST_SELLER_ID, Sellers.AMAZON)
        sellerNames.put(SECOND_SELLER_ID, Sellers.SAMSUNG)
        sellerNames
    }

    @Bean
    SellerInitializer sellerInitializer() {
        new SellerInitializer()
    }

    @Bean("proposalInitializer")
    ProposalInitializer proposalInitializer() {
        new ProposalInitializer()
    }

    @Bean
    UserInitializer userInitializer(UserService userService) {
        new UserInitializer(userService)
    }

    @Bean
    ProductInitializer productInitializer(ProductService productService) {
        new ProductInitializer(productService)
    }

    @Bean
    UserRepositoryFactory userRepositoryFactory() {
        new UserRepositoryFactory()
    }

    @Autowired
    private Environment env

    @Bean
    UserRepository userRepository(UserRepositoryFactory userRepositoryFactory) {
        userRepositoryFactory.createUserRepository()
    }

    @Bean
    OrderRepository orderRepository() {
        OrderMapRepository orderMapRepository = new OrderMapRepository()
        orderMapRepository.setSequence(Long.parseLong(env.getProperty("intitialSequence")))
        orderMapRepository
    }

    @Bean
    ItemMapRepository itemMapRepository() {
        new ItemMapRepository()
    }

    @Bean
    ProductMapRepository productMapRepository() {
        new ProductMapRepository()
    }

    @Bean
    ProposalMapRepository proposalMapRepository() {
         new ProposalMapRepository()
    }

    @Bean
    SellerMapRepository sellerMapRepository() {
        new SellerMapRepository()
    }

    @Bean
    ItemService itemService(ItemRepository repository) {
        new ItemServiceImpl(repository)
    }

    @Bean
    OrderService orderService() {
        new OrderServiceImpl()
    }

    @Bean
    ProductService productService(ProductRepository repository) {
        new ProductServiceImpl(repository)
    }

    @Bean
    ProposalService proposalService(ProposalRepository repository) {
        new ProposalServiceImpl(repository)
    }

    @Bean
    SellerService sellerService() {
        new SellerServiceImpl()
    }

    @Bean
    UserService userService() {
        new UserServiceImpl()
    }
}
