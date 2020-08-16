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
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
@Setter
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

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

    @Column(name = "address", nullable = false)
    @NotBlank
    @Size(min = 6, max = 255)
    private String address;

    @Column(name = "total", nullable = false)
    @Min(0)
    private int total;

    @Column(name = "date_of_sale", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dateOfSale;

    @OneToMany(mappedBy = "order",
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<ProductsInOrder> productsInOrders;
}
