package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//클래스 레벨에서 Controller 를 쓰거나 아래 두개를 쓰면 핸들러로 인식

//@Component
//@RequestMapping

@Controller
public class SpringMemberFormControllerV1 {

    // 메서드 명은 상관없음
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process(){
        return new ModelAndView("new-form");
    }

}
