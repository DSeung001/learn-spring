package hello.servlet.basic;

// JSON 라이브러리 자바 프로퍼티에 값을 넣어 줌!

import lombok.Getter;
import lombok.Setter;

// lombok 설치하고, Enable annotation processing 체크해야 사용 가능
// 라라벨의 모델과 같이 사용되는 자바의 프로퍼티
@Getter @Setter
public class HelloData {
    private String username;
    private int age;
}
