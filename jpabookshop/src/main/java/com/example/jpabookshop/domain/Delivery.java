package com.example.jpabookshop.domain;

import lombok.Getter;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    // EnumType.ORDINAL = 컬럼이 1,2,3으로
    // EnumType.STRING = 컬럼값을 스트링으로
    @Enumerated(EnumType.ORDINAL)
    private DeliveryStatus status;
}
