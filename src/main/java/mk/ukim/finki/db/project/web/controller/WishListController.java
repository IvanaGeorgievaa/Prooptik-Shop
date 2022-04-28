package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.WishList;
import mk.ukim.finki.db.project.service.WishListService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/wishlist")
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }
    @GetMapping
    public String getWishListPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username=request.getRemoteUser();
        WishList wishList=this.wishListService.getActiveWishList(username);
        model.addAttribute("products", this.wishListService.listAllProductsInWishList(wishList.getId()));
        return "wishlist";
    }
    @PostMapping("/{id}")
    public String addProductToWishList(@PathVariable Integer id, Authentication authentication){
        try{
            User client= (User) authentication.getPrincipal();
            this.wishListService.addProductToWishList(client.getUsername(),id);
        }
        catch (RuntimeException ex){
            return "redirect:/wishlist?error=" + ex.getMessage();
        }
        return "redirect:/wishlist";
    }
    @PostMapping("/remove-product/{id}")
    public String removeProductToShoppingCart(@PathVariable Integer id, Authentication authentication) {
        User client= (User) authentication.getPrincipal();
        WishList wishList = this.wishListService.removeProductFromWishList(client.getUsername(), id);
        return "redirect:/wishlist";
    }

}
