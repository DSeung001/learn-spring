package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j // 로깅
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String header(
            HttpServletRequest request,
            HttpServletResponse response,
            HttpMethod httpMethod,
            Locale locale, // locale resolver 도 있음
            @RequestHeader MultiValueMap<String, String> headerMap, // MultiValueMap 은 하나의 키에 여러 값이 올 수 있음
            @RequestHeader("host") String host,
            @CookieValue(value = "myCookie", required = false) String cookie // default 도 존재
    ) {
        log.info("request={}",request);
        log.info("response={}",response);
        log.info("httpMethod={}",httpMethod);
        log.info("locale={}",locale);
        log.info("headerMap={}",headerMap);
        log.info("header host={}",host);
        log.info("myCookie={}",cookie);
        return "ok";
    }
}
