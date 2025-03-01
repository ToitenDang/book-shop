package com.dang.book_shop.controller;

import com.dang.book_shop.dto.request.OrderCreationRequest;
import com.dang.book_shop.dto.request.OrderUpdateRequest;
import com.dang.book_shop.dto.response.ApiResponse;
import com.dang.book_shop.entity.Order;
import com.dang.book_shop.enums.OrderStatus;
import com.dang.book_shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public void createOrder(@RequestBody OrderCreationRequest request){
        orderService.createOrder(request);
    }

    @GetMapping
    public ApiResponse<List<Order>> getOrders(){
        return ApiResponse.<List<Order>>builder()
                .result(orderService.getAllOrders())
                .build();
    }

    @GetMapping("/user-order/{userId}")
    public ApiResponse<List<Order>> getOrdersByUserId(@PathVariable String userId){
        return ApiResponse.<List<Order>>builder()
                .result(orderService.getOrdersByUserId(userId))
                .build();
    }

    @PutMapping("/update/{orderId}")
    public void updateOrder(@PathVariable String orderId, @RequestBody OrderUpdateRequest request){
        orderService.updateOrderStatus(orderId, request);
    }


}
