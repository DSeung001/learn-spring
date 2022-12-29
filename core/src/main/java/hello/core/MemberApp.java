package hello.core;

import hello.core.memeber.Grade;
import hello.core.memeber.Member;
import hello.core.memeber.MemberService;
import hello.core.memeber.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        Java code
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = new MemberServiceImpl();
//        MemberService memberService = appConfig.memberService();

//        ApplicationContext가 Bean을 관리
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("newMember = "+member.getName());
        System.out.println("findMember = "+findMember.getName());
    }
}
