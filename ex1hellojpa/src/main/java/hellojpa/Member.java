package hellojpa;

import javax.persistence.*;
import hellojpa.Team;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    // 일대다 양방향을 억지로 넣어보자
    // 매핑되지만 읽기 전용으로 만드는 것
    @ManyToOne
    @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
    private Team team;

    // 조인컬럼 디폴트가 별로이기도 하고 가독성을 위헤 넣는거 추천
    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

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
}
