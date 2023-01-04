package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void AutowiredOption() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean {

        /* Member는 빈이 아니다, 자바 클래스이다  */

        /*
        * 없으면 호출이 안됨
        * */
        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println("noBean1 = " + noBean1);
        }

        /*
        * @Nullable 호출은 되지만, null로 들어옴
        * */
        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println("noBean2 = " + noBean2);
        }

        /*
        * 자바 8에서 추가된 옵션 문법
        * */
        @Autowired
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println("noBean3 = " + noBean3);
        }

        /*
        * Nullable과 Optional은 스프링 전반에 걸쳐 지원한다, 생성자 자동 주입에서 특정 필드에만 사용도 가능
        * */
    }
}
