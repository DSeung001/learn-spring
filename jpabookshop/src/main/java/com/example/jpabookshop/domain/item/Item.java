package com.example.jpabookshop.domain.item;

import com.example.jpabookshop.domain.Category;
import com.example.jpabookshop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Item {
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * Stock 증가
     * @param quantity
     */
    /* 비즈니스 로직 */
    /* 도메인 주도 개발 시 엔티티안에 비즈니스 로직을 만듦 */
    public void addStock(int quantity){
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity){
        int restStock = this.stockQuantity - quantity;
        if(restStock < 0){
            throw new NotEnoughStockException("ned more stock");
        }
        this.stockQuantity = restStock;
    }

}
