package hello.core.discount;

import hello.core.memeber.Grade;
import hello.core.memeber.Member;

public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmount = 1000;


    @Override
    public int discount(Member member, int price) {
        // enum 타입은 == 쓰는 게 맞다
        if (member.getGrade() == Grade.VIP){
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
