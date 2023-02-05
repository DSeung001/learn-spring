package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MemberProduct {

    // 따로 아이디를 부여하는 게 확장성에 좋음
    // 모든 테이블에 일관성있게 아이디 까는게 실무에 좋음
    @Id @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Member product;

    private int count;
    private int price;
    private LocalDateTime orderDateTime;
}
