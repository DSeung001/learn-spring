package hello.helleSpring.repository;

import hello.helleSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    /*
     * Repository 클래스는 기계적인(개발적인) 메서드 네이밍
     *
     * */

    /*
        Optional => null이 올 수도 있다.
        요즘은 null 처리를 Optional로 감싸는 것을 선호함
    */
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
