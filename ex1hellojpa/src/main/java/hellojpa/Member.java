package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

// JPA를 사용하는 얘라고 인식하는 Annotation
// 만약 테이블명이 클래스명과 다르면 @Table(name="USER")과 같이 추가 해야함
@Entity
public class Member {
    @Id
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default 'EMPTY'")
    private String username;
    private int age;
    @Enumerated(EnumType.STRING)
    // @Enumerated(EnumType.STRING) 이렇게 안쓰고 기본으로 쓰면 ORDINAL로 숫자로 매핑함 ex:0,1,2
    // => ORDINAL로 할 경우 문제는 새로운 enum 추가시 순서가 바뀜
    // Enum 클래스 사용, DB 마다 enum 타입이 존재하는 것도 존재하지만 없는 게 많음
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    // 최신 버전은 아래코드 사용!
    private LocalDate testLocalDate;
    private LocalDateTime testLocalDateTime;

    @Lob
    // varchar 보다 큰 콘텐츠는 Lob을 사용
    private String description;

    @Transient
    // 테이블이 아닌 메모리에서만 사용한다로 저으이 가능
    private int temp;

    public Member(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
