package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 스프링 빈의 이름을 /springmvc/old-controller로, url 패턴에 맞춤
@Component("/springmvc/old-controller")
public class OldController implements Controller {
    /*
    * 핸들러 매핑에서 이 컨트롤러를 찾는 방법
    * 1. 스프링 빈의 이름으로 핸들러를 찾을 수 있는 핸들러 매핑이 필요하다
    * 2. interface Controller 인터페이스를 실행할 수 있는 핸들러 어댑터를 찾고 실행해야 함
    * 3. 스프링은 이미 대부분 핸들러 어댑터를 만듦
    *
    * */

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
