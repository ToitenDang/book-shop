package com.dang.book_shop.service;

import com.dang.book_shop.dto.request.OrderCreationRequest;
import com.dang.book_shop.dto.request.OrderUpdateRequest;
import com.dang.book_shop.entity.*;
import com.dang.book_shop.enums.OrderStatus;
import com.dang.book_shop.exception.AppException;
import com.dang.book_shop.exception.ErrorCode;
import com.dang.book_shop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    public void createOrder(OrderCreationRequest request){
        // Lấy user từ dbms = userId
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Tạo 1 đơn hàng mới và đưa dữ liệu từ request vào đối tượng đơn hàng
        Order order = new Order();
        order.setUser(user);
        order.setAddress(request.getAddress());
        order.setPhone(request.getPhone());
        order.setFee(request.getFee());
        order.setNote(request.getNote());
        order.setStatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        order.setShipDate(LocalDate.now().plusDays(3));

        // Lấy ra giỏ hàng của người dùng
        Cart cart = cartRepository.findByUserId(request.getUserId());


        if(cart != null){
            // Lấy tổng tiền từ giỏ hàng đưa vào đơn hàng
            order.setTotalPrice(cart.getTotalPrice());
            // Lưu đơn hàng vào dbms
            orderRepository.save(order);

            // Lấy ra danh sách sản phẩm đã được thêm vào cart
            List<CartDetail> cartDetails = cart.getCartDetail();
            for (CartDetail cartDetail : cartDetails){
                // Tạo đối tượng orderDetail để lưu thông tin sản phẩm được đặt hàng
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder(order);
                orderDetail.setBook(cartDetail.getBook());
                orderDetail.setPrice(cartDetail.getPrice());
                orderDetail.setQuantity(cartDetail.getQuantity());

                // Lưu orderDetail vào dbms
                orderDetailRepository.save(orderDetail);

                // Cập nhật lại số lượng sách trong kho
//                Book book = cartDetail.getBook();
//                if (book.getQuantity() < cartDetail.getQuantity()) {
//                    throw new RuntimeException("Insufficient stock for product: " + book.getName());
//                }
//                book.setQuantity(book.getQuantity() - cartDetail.getQuantity());
//                book.setSold(book.getSold() + cartDetail.getQuantity());
//                bookRepository.save(book);
            }


            cartService.clearCart(cart.getId());
//            // Xóa các sản phẩm đã đătj khỏi giỏ hàng
//            cartService
//            for (CartDetail cartDetail: cartDetails){
//                cartDetailRepository.delete(cartDetail);
//            }
//            // Xóa giỏ hàng
//            cartRepository.delete(cart);
        }
    }

    public List<Order> getOrdersByUserId(String userId){
        return orderRepository.findAllOrderByUserId(userId);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(String orderId){
        return orderRepository.findById(orderId).orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_FOUND));
    }

    public void updateOrderStatus(String orderId, OrderUpdateRequest request) {
        try {
            OrderStatus newStatus = OrderStatus.valueOf(request.getNewStatus());
            Order order = getOrderById(orderId);
            order.setStatus(newStatus);
            orderRepository.save(order);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid OrderStatus: " + request.getNewStatus());
        }
    }
}
