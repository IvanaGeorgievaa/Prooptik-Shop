package mk.ukim.finki.db.project.web.controller;

import mk.ukim.finki.db.project.model.Client;
import mk.ukim.finki.db.project.model.Order;
import mk.ukim.finki.db.project.model.exceptions.UserNotFoundException;
import mk.ukim.finki.db.project.reporitory.UserRepository;
import mk.ukim.finki.db.project.service.OrderService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderService orderService;
    private final UserRepository userRepository;

    public OrdersController(OrderService orderService, UserRepository userRepository) {
        this.orderService = orderService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest request, Model model){
        if(error!=null && !error.isEmpty()){
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username=request.getRemoteUser();
        Order order=this.orderService.getActiveShoppingCart(username);
        model.addAttribute("products", this.orderService.listAllProductsInShoppingCart(order.getId()));
        return "orders";
    }
    @PostMapping("/add-product/{id}")
    public String addProductToShoppingCart(@PathVariable Integer id, Authentication authentication){
        try{
            User client= (User) authentication.getPrincipal();
            this.orderService.addProductToShopping(client.getUsername(),id);
        }
        catch (RuntimeException ex){
            return "redirect:/orders?error=" + ex.getMessage();
        }
        return "redirect:/orders";
    }
    @PostMapping("/remove-product/{id}")
    public String removeProductToShoppingCart(@PathVariable Integer id, Authentication authentication) {
        User client= (User) authentication.getPrincipal();
        Order order = this.orderService.removeProductFromShoppingCart(client.getUsername(), id);
        return "redirect:/orders";
    }
    @PostMapping("/success")
    public String getSuccessOrderPage(Authentication authentication){
        User client= (User) authentication.getPrincipal();
        Order order=this.orderService.checkoutShoppingCart(client.getUsername());
        return "SuccessOrderPage";
    }

    @GetMapping("/finished")
    public String getFinishedOrders(Authentication authentication, Model model){
        User client= (User) authentication.getPrincipal();
        Client c=this.userRepository.findByUsername(client.getUsername()).orElseThrow(()->new UserNotFoundException(client.getUsername()));
        String username=c.getUsername();
        model.addAttribute("finished", orderService.listAllFinishedByClient(username));
        return "finishedOrders";
    }
}
