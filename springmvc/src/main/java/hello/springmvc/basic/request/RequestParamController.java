package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    /**
     * HTTP Servlet의 요청 파라미터 조회
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}",username, age);

        //  return 타입 void 에서 response 에다가 값을 적으면 그대로 출력
        response.getWriter().write("ok");
    }

//     @ResponseBody 를 쓰면 RestController 처럼 리턴을 바로 HTTP 바디에
    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ){
        log.info("username={}, age={}", memberName,memberAge);
        return "ok";
    }


    // @RequestParam 에서 동일하면 매개변수 생략가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ){
        log.info("username={}, age={}", username,age);
        return "ok";
    }


    // @RequestParam 에서 동일하고 String, Int, Integer 등의 단순 타입 전부 생략 가능
    // 하지만 @RequestParam 을 생략하면 스프링에 대한 이해도가 낮으면 모를 수 있음
    // 김영한 개발자님은 넣는 게 취향에 맞대
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(
          String username,
          int age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // required 기본값은 true
    // 기본 자료형에는 null이 못들어가기에 required = false로 할 수 없음
    // 하려면 객체형인 Integer로 바꿔줘야함
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            @RequestParam(required = true)  String username,
            @RequestParam(required = false) Integer age
    ){
        // null != ""
        // 그러니까 username에 ""이 들아올 수 있다.
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // required 여부와 상관없이 defaultValue 넣을 수 있음
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest")  String username,
            @RequestParam(required = false, defaultValue = "-1") Integer age
    ){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // 모든 파라미터를 받겠다 => map 또는 multiValueMap 으로 받을 수 있다
    // 차이점은 multiValueMap 은 하나의 키에 여러 개의 값이 올 수 있다는 것
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap
    ){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }
}