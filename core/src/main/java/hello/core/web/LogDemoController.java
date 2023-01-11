package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
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
    // Request 스코프인데 Request가 없으면 못사는데 스프링에서 Request가 없이 불러서 에러 반환
    // 이는 Provider를 쓰면 해결됨
    private final MyLogger myLogger;

    // 뷰 없이
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURI().toString();
        // myLogger는 Requset 스코프
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
