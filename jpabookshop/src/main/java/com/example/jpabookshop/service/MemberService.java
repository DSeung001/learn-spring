package com.example.jpabookshop.service;

import com.example.jpabookshop.domain.Member;
import com.example.jpabookshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
// @AllArgsConstructor
@RequiredArgsConstructor // final 에 대해 알아서 생성자 주입을 만들어 줌
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자로 의존성 주입, 생성자가 하나만 있으면 알아서 의존성을 주입해줌 (스프링에서) => ioc
    /*public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    // Setter로 의존성 주입
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    /**
     * 회원 가입
     * @param member
     * @return
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    // 아래 로직만 넣으면 동시 요청시 두개가 저장될 수 있음 => DB에 유니크 걸어두자
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원전체 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
