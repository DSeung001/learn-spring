package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);

        // [status-line]
        // 200을 직접 넣는 것 보다 아래께 유지뷰수에 좋음
        resp.setStatus(HttpServletResponse.SC_OK);
//        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);

        // [response-headers]
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, must-revalidate");
        resp.setHeader("Pragma", "no-cache"); // 과거까지 캐시를 지움
        resp.setHeader("my-header", "hello"); // 임의의 헤더를 생성

        // [Header 편의 메서드]
//        content(resp);
//        cookie(resp);
        redirect(resp);

        // [message body]
        PrintWriter writer = resp.getWriter();
        writer.println("안녕하세요.");
    }

    private void content(HttpServletResponse response){
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2) // 생략시 자동 생성 => 원래는 Content-Length 가 필수 값
    }

    private void cookie(HttpServletResponse response){
        // 쿠키도 원래 해더로 넣을 수 있는 데 편의성을 위해 객체 사용
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException{
        // status code 302
        /*response.setStatus(HttpServletResponse.SC_FOUND);
        response.setHeader("Location", "/basic/hello-form.html");*/
        // 위 두줄과 같은 기능
        response.sendRedirect("/basic/hello-form.html");
    }
}
