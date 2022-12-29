package hello.core.member;

/*
enumeration type : 열거체로, 상수를 클래스처럼 보이게 해주는 것
1. 열거형으로 선언된 순서에 따라 0부터 index 값을 가진다.(순차적으로 증가)
2. enum 열거형으로 지정된 상수들은 모두 대문자로 선언한다.
3. 열거형 변수들을 선언한 후 마지막에 세미콜론(;)을 찍지 않는다.
4. 상수와 특정 값을 연결시킬경우 마지막에 세미콜론(;)을 붙여줘야한다.
*/

public enum Grade {
    BASIC,
    VIP
}
