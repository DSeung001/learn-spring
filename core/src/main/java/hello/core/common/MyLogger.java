package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init(){
        // Global하게 Unique한 아이디 생성
        // 확률이 로또의 로또의 로또보다 낮을 거임
        UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:"+this);
    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:"+this);
    }
}
