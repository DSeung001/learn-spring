package com.example.jpabookshop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name ="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // Order 테이블의 member에 의해 매핑됐다. => 읽기 전용
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
