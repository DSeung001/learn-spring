package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    // 최신 HTTP 스펙에서는 GET에서도 바디에 데이터를 담을 수 있지만 실무에서는 안 그러니 Get 으로
    @PostMapping("/request-body-sring-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }


    // 객체로 받을 수 있음 => 문서 받을 수 있는 객체가 나와 있음!
    @PostMapping("/request-body-sring-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        responseWriter.write("ok");
    }


    // HTTP 메세지 컨버터 사용
    // ResponseEntity, RequestEntity 둘 다 HttpEntity 를 상속받은 거
    @PostMapping("/request-body-sring-v3")
    public HttpEntity<String > requestBodyStringV3(RequestEntity<String> httpEntity) throws IOException {

        String messageBody = httpEntity.getBody();
        log.info("messageBody={}", messageBody);

        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
    }

    // 애노테이션이 최고야
    // @ResponseBody는 응답 결과를 HTTP 메시지 바디에 직접 담아서 전달
    // 해더 정보가 필요하면 @RequestHeader를 사용하면 됨
    // 이렇게 바디를 조회하는 건 요청 파라미터를 조회하는 @RequestParam, @ModelAttribute 와는 전혀 관계 없음!
    @ResponseBody
    @PostMapping("/request-body-sring-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}", messageBody);
        return "ok";
    }
}
