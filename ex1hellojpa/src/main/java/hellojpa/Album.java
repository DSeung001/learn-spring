package hellojpa;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
// 서브타입, 자식에서 슈퍼타입의 @DiscriminatorColumn에 넣을 값을 결정, 기본이 Entity 명칭
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;
}
