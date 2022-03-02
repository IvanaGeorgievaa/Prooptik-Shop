package mk.ukim.finki.db.project.service.impl;

import mk.ukim.finki.db.project.model.*;
import mk.ukim.finki.db.project.model.exceptions.*;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.reporitory.WishListRepository;
import mk.ukim.finki.db.project.service.ProductService;
import mk.ukim.finki.db.project.service.WishListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WishListServiceImpl implements WishListService {
    private final WishListRepository wishListRepository;
    private final ProductService productService;
    private final UserRepository userRepository;

    public WishListServiceImpl(WishListRepository wishListRepository, ProductService productService, UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @Override
    public List<Product> listAllProductsInWishList(Integer id) {
        if (!this.wishListRepository.findById(id).isPresent())
            throw new WishListNotFoundException(id);
        return this.wishListRepository.findById(id).get().getProducts();
    }

    @Override
    public void addProductToWishList(String username, Integer productId) {
        WishList wishList = this.getActiveWishList(username);
        Product product = this.productService.findById(productId);

        if (wishList.getProducts()
                .stream().filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList()).size() > 0)
            throw new ProductAlreadyInWishListException(productId, username);
        wishList.getProducts().add(product);
        this.wishListRepository.save(wishList);
    }

    @Override
    public WishList getActiveWishList(String username) {
        Client user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return this.wishListRepository
                .findByClientAndStatus(user, WishListStatus.CREATED)
                .orElseGet(() -> {
                    WishList wishList = new WishList(user);
                    return this.wishListRepository.save(wishList);
                });
    }
}
