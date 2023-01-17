package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class LogTestController {

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";
        // 이 두개는 일반적으로 안남음
        log.trace("info log={}",name);
        log.debug("info log={}",name);
        log.info("info log={} {}",name, name);
        log.info("info log={}",name);
        log.warn("info log={}",name);
        log.error("info log={}",name);
        return "ok";
    }
}
