package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
// 아래 final에 자동 주입
@RequiredArgsConstructor
public class LogDemoController
{
    // 자동 주입
    private final LogDemoService logDemoService;
    private final  MyLogger myLogger;

    // 뷰 없이 localhost:포트/log-demo
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        System.out.println("myLogger = " + myLogger.getClass());
        // 결과 값이 myLogger = class hello.core.common.MyLogger$$EnhancerBySpringCGLIB$$700597e
        // SpringCGLIB => 뭔가 스프링이 조작하는 느낌
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(2000);
        // request scope이므로 아래에서도 실행!
        logDemoService.logic("testId");
        System.out.println("myLogger = " + myLogger.getClass());
        return "OK";
    }
}
