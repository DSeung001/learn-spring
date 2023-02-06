package hellojpa;

import javax.persistence.*;

@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
// @DiscriminatorColumn을 넣어주면 조인 전략에서 서브 타입 구분자를 슈퍼 타입에 컬럼으로 넣어줌
// SINGLE_TABLE은 구조상 무조건 DiscriminatorColumn 생김
@DiscriminatorColumn(name="D_TYPE")
// 추상 클래스로 만드는게 맞음
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
