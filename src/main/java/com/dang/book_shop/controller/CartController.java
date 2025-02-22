package com.dang.book_shop.controller;

import com.dang.book_shop.entity.Cart;
import com.dang.book_shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable String userId){
        return cartService.getCartByUserId(userId);
    }

    @DeleteMapping("/{cartId}/remove/{itemId}")
    public void deleteCartDetail(@PathVariable String cartId,
                                 @PathVariable String itemId){
        cartService.deleteCartItem(cartId, itemId);
    }

    @PutMapping("/{cartId}/update/{itemId}")
    public void updateCartDetail(@PathVariable String cartId,
                                 @PathVariable String itemId,
                                 @RequestParam int quantity){
        cartService.updateCartDetail(cartId, itemId, quantity);
    }
}
