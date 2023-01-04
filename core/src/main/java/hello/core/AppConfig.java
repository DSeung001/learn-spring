package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 팩토리 메소드를 통해서 설정하는 방법
@Configuration
public class AppConfig {

    // Bean을 붙임으로 컨테이너에 등록이 됨

    // 이제 MemberServiceImpl는 인터페이스에만 의존 => DIP를 지킴!
    // => 이걸 생성자 주입이라 함
    @Bean
    public MemberService memberService(){
        System.out.println("AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    /*
    * @Bean에 설정해둔 메서드에 static을 붙이면 이 부분을 사용하는 곳이 다른 주소를 나타냄
    * static이 없으면 사용하는 곳은 전부 같은 걸 사용
    * */
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService(){
        System.out.println("AppConfig.orderService");
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
        return null;
    }

    @Bean
    public DiscountPolicy discountPolicy(){
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}