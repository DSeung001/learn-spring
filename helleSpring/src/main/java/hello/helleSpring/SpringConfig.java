package hello.helleSpring;

import hello.helleSpring.aop.TimeTraceApp;
import hello.helleSpring.repository.MemberRepository;
import hello.helleSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/* Bean을 Component가 아닌 직접 코드로 추가 */

@Configuration
public class SpringConfig {

    private EntityManager em;
    private DataSource dataSource;
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(EntityManager em, DataSource dataSource, MemberRepository memberRepository){
        this.em = em;
        this.dataSource = dataSource;
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//        return
//    }
}
