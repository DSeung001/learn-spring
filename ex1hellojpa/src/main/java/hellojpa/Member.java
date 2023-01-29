package hellojpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// JPA를 사용하는 얘라고 인식하는 Annotation
// 만약 테이블명이 클래스명과 다르면 @Table(name="USER")과 같이 추가 해야함
@Entity
public class Member {

    @Id
    private Long id;
    // 컬럼명도 @Column(name = "username")으로 가능
    @Column(unique = true, length = 10)
    private String name;
    private int age;

    // JPA는 리다이렉션을 자주하기에 기본 생성자가 존재해야 에러 발생 x
    public Member(){

    }

    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
