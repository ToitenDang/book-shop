package com.dang.book_shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
}
