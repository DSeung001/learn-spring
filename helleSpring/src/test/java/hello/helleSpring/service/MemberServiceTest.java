package hello.helleSpring.service;

import hello.helleSpring.domain.Member;
import hello.helleSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class MemberServiceTest {

    // 이런 단위 테스트도 속도 때문에 매우 잘쓰면 매우 좋다~

    /*
        테스트는 한글로 바꿔도 됨
        => 영어권 사람들과 같이 일하는게 아니면 한글로도 자주함
    */

    MemberService memberService ;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void beforeEach(){
        // 제어 역전 DI
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertEquals(member.getName(),findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        /*
        try catch 문 대신 추천
        */
        memberService.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            memberService.join(member2);
        });
        Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");

        /*try {
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e ){
            Assertions.assertEquals(e.getMessage(), "이미 존재하는 회원입니다.");
        }*/
        // then
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {

    }
}