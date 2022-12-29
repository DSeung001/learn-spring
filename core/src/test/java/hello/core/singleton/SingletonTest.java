package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 DI 컨테이너")
    void pureContainer(){
        AppConfig appConfig = new AppConfig();
        // 1. 조회 호출 할 때마다 새로운 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        // 2. 조회 호출 할 때마다 새로운 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 = "+memberService1);
        System.out.println("memberService2 = "+memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singletonServiceTest(){
        SingletonService singletonService1 = SingletonService.getInstance();
        SingletonService singletonService2 = SingletonService.getInstance();

        System.out.println("memberService1 = "+singletonService1);
        System.out.println("memberService2 = "+singletonService2);

        assertThat(singletonService1).isSameAs(singletonService2);
    }

//     스프링의 빈을 사용함으로써 싱글톤을 사용함과 동시에 기존에 가지던 여러 문제를 해결가능하다.
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 =  ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = "+memberService1);
        System.out.println("memberService2 = "+memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
}
