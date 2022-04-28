package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.Transaction;
import mk.ukim.finki.db.project.model.enumerations.OrderStatus;
import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.model.exceptions.*;
import mk.ukim.finki.db.project.reporitory.FinishedOrdersViewRepository;
import mk.ukim.finki.db.project.reporitory.OrderRepository;
import mk.ukim.finki.db.project.reporitory.TransactionRepository;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.service.OrderService;
import mk.ukim.finki.db.project.service.ProductService;
import mk.ukim.finki.db.project.views.FinishedOrdersView;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserRepository userRepository;
    private final ProductService productService;
    private final OrderRepository orderRepository;
    private final TransactionRepository transactionRepository;
    private final FinishedOrdersViewRepository finishedOrdersViewRepository;

    public OrderServiceImpl(UserRepository userRepository, ProductService productService, OrderRepository orderRepository, TransactionRepository transactionRepository, FinishedOrdersViewRepository finishedOrdersViewRepository) {
        this.userRepository = userRepository;
        this.productService = productService;
        this.orderRepository = orderRepository;
        this.transactionRepository = transactionRepository;
        this.finishedOrdersViewRepository = finishedOrdersViewRepository;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Integer cartId) {
        if (!this.orderRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.orderRepository.findById(cartId).get().getProducts();
    }

    @Override
    public Order getActiveShoppingCart(String username) {
        Client user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.orderRepository
                .findByClientAndStatus(user, OrderStatus.CREATED)
                .orElseGet(() -> {
                    Order order = new Order(user);
                    return this.orderRepository.save(order);
                });
    }

    @Override
    public void addProductToShopping(String username, Integer productId) {
        Order order = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId);

        if (order.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInShoppingCartException(productId, username);
        order.getProducts().add(product);
        this.orderRepository.save(order);
    }

    @Override
    public Order removeProductFromShoppingCart(String userId, Integer productId) {
        Order order = this.getActiveShoppingCart(userId);
        order.setProducts(order.getProducts()
                .stream()
                .filter(product -> !product.getId().equals(productId))
                .collect(Collectors.toList()));
        return this.orderRepository.save(order);
    }

    @Override
    public Order checkoutShoppingCart(String userId) {
        Client client=this.userRepository.findByUsername(userId).orElseThrow();
        Order order=this.orderRepository.findByClientAndStatus(client,OrderStatus.CREATED)
                .orElseThrow(()-> new ShoppingCartIsNotActiveException(userId));

        order.setStatus(OrderStatus.FINISHED);
        return this.orderRepository.save(order);
    }

    @Override
    public List<FinishedOrdersView> listAllFinishedByClient(String username) {
        return this.finishedOrdersViewRepository.findAll()
                .stream().filter(p->p.getUsername().equals(username)).collect(Collectors.toList());
    }

    @Override
    public Order findById(Integer id) {
        return this.orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found!"));
    }

    @Override
    @Transactional
    public Transaction createTransaction(Integer orderId, String transactionHash, String fromAddress, String toAddress, String amount) {
        Transaction transaction = new Transaction(
                transactionHash,
                this.findById(orderId),
                toAddress,
                fromAddress,
                amount
        );
        return this.transactionRepository.save(transaction);
    }
}
