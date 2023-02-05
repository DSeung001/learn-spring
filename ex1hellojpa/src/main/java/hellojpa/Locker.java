package hellojpa;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Locker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 마찬가지로 주인이 아닌 쪽은 mappedBy로 읽기전용으로 함
    @OneToOne(mappedBy = "MEMBER_ID")
    private Member member;
}
