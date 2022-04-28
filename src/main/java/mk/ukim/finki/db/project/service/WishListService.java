package mk.ukim.finki.db.project.service;

import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.Product;
import mk.ukim.finki.db.project.model.WishList;

import java.util.List;

public interface WishListService {
    List<Product>listAllProductsInWishList(Integer id);
    void addProductToWishList(String username, Integer productId);
    WishList getActiveWishList(String username);
    WishList removeProductFromWishList(String userId, Integer productId);
}
