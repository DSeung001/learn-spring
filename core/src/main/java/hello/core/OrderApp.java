package hello.core;

import hello.core.memeber.Grade;
import hello.core.memeber.Member;
import hello.core.memeber.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();

//        Java code
//        MemberService memberService = new MemberServiceImpl();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = new OrderServiceImpl();
//        OrderService orderService = appConfig.orderService();

        ApplicationContext applicationContext  = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 20000);

        System.out.println("order = "+order);
        System.out.println("order = "+order.calculatePrice());

    }
}
