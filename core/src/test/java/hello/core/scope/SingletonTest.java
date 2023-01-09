package hello.core.scope;

import hello.core.annotation.MainDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean singleTonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean singleTonBean2 = ac.getBean(SingletonBean.class);

        System.out.println("singleTonBean1 = " + singleTonBean1);
        System.out.println("singleTonBean2 = " + singleTonBean2);

        Assertions.assertThat(singleTonBean1).isSameAs(singleTonBean2);
        ac.close();
    }

    // AnnotationConfigApplicationContext 등록해줘서 @Component는 생력
    @Scope("singleton")
    static class SingletonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destroy");
        }
    }
}
