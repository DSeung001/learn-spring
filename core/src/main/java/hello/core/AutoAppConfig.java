package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

// @Configuration도 @Component를 사용
@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)
) // 수동으로 빈을 등록한 AppConfig 제외, + 다른 configuration 들도 제외
public class AutoAppConfig {

}
