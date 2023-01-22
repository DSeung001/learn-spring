package hello.itemservice.domain.item;

import lombok.Getter;
import lombok.Setter;

// @Data 사용은 핵심 도메인에 사용하기 어려움, 왜냐하면 예측하기 어렵기 때문
@Getter @Setter
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item(){

    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
