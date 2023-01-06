package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    // final로 불변처리, private에는 의존관계 주입 가능
    // 얘네는 DI 프레임워크가 필수로 사용되어야 함
    // 사용해도 되는 곳 => 애플리케이션과 상관 없는 테스트 코드
//    @Autowired private MemberRepository memberRepository;
//    @Autowired private DiscountPolicy discountPolicy;

    // 생성자에서만 값을 주입할 수 있음, 만약 final 값을 생성자에서 초기화 안하면 에러를 발생시킴 (자바 컴파일러가)
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /*public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }*/

    // 생성자가 하나일 때는 자동으로 Autowired를 해줘서 생략이 가능
//    @Autowired
//    @RequiredArgsConstructor 이게 아래 코드를 똑같이 생성
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        System.out.println("OrderServiceImpl.OrderServiceImpl");
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }

//    일반 메서드 주입, 한번에 여러 필드를 주입 받을 수 있다 => 보통 생성자로 대체함
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy){
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 싱글톤인지 아닌지 확인을 위한 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
