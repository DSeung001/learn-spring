package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
* 1. 파라미터 전송 기능
 * https://localhost/reqeust-param?username=hello&age=20
 * 여기서 Parameter는 GET과 POST에 구분을 두지 않음
 * 같은 application/x-www-form-urlencoded 형식
*/
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);

        System.out.println("RequestParamServlet.service");
        System.out.println("[전체 파라미터 조회] - start");
        req.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println(paramName +"="+req.getParameter(paramName)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        String username = req.getParameter("username");
        System.out.println("username = " + username);
        String age = req.getParameter("age");
        System.out.println("age = " + age);
        System.out.println("[단일 파라미터 조회] - end");
        resp.getWriter().write("request-param");
        System.out.println();

        // 같은 파라미터명으로 올 경우, getParameterValeus로 사용 가능
        System.out.println("[이름이 같은 복수 파라미터 조회]");
        String[] usernames = req.getParameterValues("username");
        for (String name : usernames){
            System.out.println("name = " + name);
        }
    }
}
