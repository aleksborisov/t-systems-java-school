package com.marsarmy.model;

import com.marsarmy.model.enumeration.DeliveryMethod;
import com.marsarmy.model.enumeration.OrderStatus;
import com.marsarmy.model.enumeration.PaymentMethod;
import com.marsarmy.model.enumeration.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "payment_method_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PaymentMethod paymentMethod;

    @Column(name = "delivery_method_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DeliveryMethod deliveryMethod;

    @Column(name = "payment_status_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @Column(name = "order_status_id", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductsInOrder> productsInOrders;
}
