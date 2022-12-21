package hello.helleSpring.service;

import hello.helleSpring.domain.Member;
import hello.helleSpring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// 클래스에서 ctrl + shift + t
@Transactional
public class MemberService {
    /*
    * Service 클래스는 대외적인(비즈니스) 메서드 네이밍
    * */

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){
        // 같은 이름은 x

        // ctrl + alt + v => 변수 생성 및 그걸로 받음
        // Optional<Member> result = memberRepository.findByName(member.getName());
        // 바로 get으로 가져와도 되지만 권장은 x 또는
        // result.orElseGet() 이 친구 사용하기도 함

        // ctrl + shift + alt + t => 리팩토링
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).
                ifPresent(m -> {
            throw  new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
