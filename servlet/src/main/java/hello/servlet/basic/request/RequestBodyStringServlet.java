package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// 요청으로 단순 메세지를 보낼 경우 읽는 방법 (postman에서 raw 형식의 text 타입으로 데이터를 보낸 것)
// 요즘에는 이렇게 주고 받지 않지요
@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
        // 메시지 바디의 내용을 바이트 코드로 얻을 수 있음
        ServletInputStream inputStream = req.getInputStream();
        // 바이트를 UTF-8로 바꿔서 꺼내겠다
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        System.out.println("messageBody = "+messageBody);

        resp.getWriter().write("ok");

    }
}
