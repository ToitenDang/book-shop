package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.CategoryCreationRequest;
import com.dang.book_shop.entity.*;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.repository.BookRepository;
import com.dang.book_shop.repository.CartDetailRepository;
import com.dang.book_shop.repository.CartRepository;
import com.dang.book_shop.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    private final UserService userService;
    private final BookService bookService;
    private final CartRepository cartRepository;
    private final BookRepository bookRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserRepository userRepository;

    public CartService(UserService userService,
                       BookService bookService,
                       CartRepository cartRepository,
                       BookRepository bookRepository,
                       CartDetailRepository cartDetailRepository,
                       UserRepository userRepository) {
        this.userService = userService;
        this.bookService = bookService;
        this.cartRepository = cartRepository;
        this.bookRepository = bookRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userRepository = userRepository;
    }

    public void addProductToCart(String userId, String bookId, int quantity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        if (user != null) {
            Cart cart = cartRepository.findByUserId(userId);

            if (cart == null) {
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setTotalPrice(0);
                cart = cartRepository.save(newCart);
            }

            Book book = bookService.getBookById(bookId);

            if (book != null) {
                CartDetail cartDetail = cartDetailRepository.findByCartAndBook(cart, book);

                if (cartDetail != null) {
                    cartDetail.setQuantity(cartDetail.getQuantity() + quantity);
                } else {
                    cartDetail = new CartDetail();
                    cartDetail.setQuantity(quantity);
                    cartDetail.setPrice(book.getPrice());
                    cartDetail.setCart(cart);
                    cartDetail.setBook(book);
                    //int s = cart.getTotalPrice()+ (quantity * book.getPrice());
                    cart.setTotalPrice(cart.getTotalPrice() + (quantity * book.getPrice()));
                    cartRepository.save(cart);
                }
                cartDetailRepository.save(cartDetail);
            }
        }
    }

    public Cart getCartByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

    public void deleteCartItem(String cartId, String cartDetailId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(()-> new AppException(ErrorCode.CART_NOT_FOUND));
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        int newTotalPrice = cart.getTotalPrice() - (cartDetail.getPrice() * cartDetail.getQuantity());
        cart.setTotalPrice(newTotalPrice);
        //cart.getCartDetail().remove(cartDetail);
        cartDetailRepository.deleteById(cartDetailId);
        cartRepository.save(cart);
    }


    public void updateCartDetail(String cartId, String cartDetailId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        CartDetail cartDetail = cartDetailRepository.findById(cartDetailId)
                .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));

        cartDetail.setQuantity(quantity);

        int newTotalPrice = cart.getCartDetail().stream()
                .mapToInt(detail -> detail.getPrice() * detail.getQuantity())
                .sum();
        cart.setTotalPrice(newTotalPrice);

        cartDetailRepository.save(cartDetail);
        cartRepository.save(cart);
    }

    public void clearCart(String cartId){
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
        cartDetailRepository.deleteAllByCartId(cartId);
        cart.getCartDetail().clear();
        cartRepository.deleteById(cartId);
    }

//    public CartDetail getCartDetail(String cartId, String bookId){
//        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new AppException(ErrorCode.CART_NOT_FOUND));
//        return cart.getCartDetail().stream()
//                .filter(item -> item.getBook().getId().equals(bookId))
//                .findFirst().orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
//
//    }

}
