package mk.ukim.finki.db.project.service;



import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.reporitory.FinishedOrdersViewRepository;
import mk.ukim.finki.db.project.views.FinishedOrdersView;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface OrderService {
    List<Product> listAllProductsInShoppingCart(Integer id);
    Order getActiveShoppingCart(String username);
    Order addProductToShopping(String username, Integer productId);
    Order removeProductFromShoppingCart(String userId, Integer productId);
    Order checkoutShoppingCart(String userId);
    List<FinishedOrdersView> listAllFinishedByClient(String username);
}
