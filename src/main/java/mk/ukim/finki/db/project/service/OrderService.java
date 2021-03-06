package mk.ukim.finki.db.project.service;


import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.model.Transaction;
import mk.ukim.finki.db.project.views.FinishedOrdersView;

import java.util.List;

public interface OrderService {
    List<Product> listAllProductsInShoppingCart(Integer id);
    Order getActiveShoppingCart(String username);
    void addProductToShopping(String username, Integer productId);
    Order removeProductFromShoppingCart(String userId, Integer productId);
    Order checkoutShoppingCart(String userId);
    List<FinishedOrdersView> listAllFinishedByClient(String username);

    Order findById(Integer id);

    Transaction createTransaction(Integer orderId, String transactionHash, String fromAddress, String toAddress, String amount);
}
