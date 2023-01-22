package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    // Controlloer에서 반환을 스트링으로 하면 경로에 맞는 뷰를 반환한다
    // 해당 방법을 추천함.
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!2");
        return "response/hello";
    }

    // 김영한 개발자님은 권장하지 않는 방법, 이유는 불명확하기 때문
    // RequestMapping과 동일한 뷰 템플릿이 존재하면 생략 가능
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data", "hello!2");
    }
}
