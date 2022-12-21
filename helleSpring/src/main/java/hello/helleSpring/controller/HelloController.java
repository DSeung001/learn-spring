package hello.helleSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // GET, hello에 매핑
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute( "data", "hello!!");

        // resources/templates/hello
        // 템플릿 처리하므로
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    /*
    API
    @ResponseBody => 응답 몸체에 넣겠다.
    */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello "+name;
    }

    @GetMapping("hello-api")
    @ResponseBody // Json 반환이 기본, 세팅
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        // 스프링은 객체가 리턴을 하면, JSON으로 반환하기로 정함
        // 객체를 보고 HttpMessageConverter를 통해 객체면 JsonConverter로 진행함 => MappingJackson2HttpMessageConverter
        // 기본 스트링이면 StringConverter를 실행

        // HttpAccept 해더를 통해 컨트롤러 반환타입을 정의한 후 Converter를 선택함
        return hello;
    }

    /* JavaBean 규약 */
    static class Hello {
        private String name;

        // 단축키 insert + alt
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}