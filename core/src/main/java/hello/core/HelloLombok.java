package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    /* Lombok의 getter, setter 자동 생성 */
    /* 추가적으로 생성자도 존재 => 사이트 체크 필요 */
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("asdfas");;

        String name = helloLombok.getName();
        System.out.println("name = " + name);

        System.out.println(helloLombok);

    }
}
