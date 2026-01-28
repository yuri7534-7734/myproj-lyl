public class ex02 {
    public static void main(String[] args) {
        //출력문
        System.out.println("화면출력");
        System.out.println("화면"+"출력");
        System.out.println("10"+20);
        System.out.println(10+"20");
        System.out.println(10+20);
        //soutm, soutp, soutv 자주쓰임

        //println print printf (별찍기)
        // ln(line) 줄바꿈을 시킨다.
        System.out.println("한줄출력후 줄바꿈");
        System.out.print("한줄출력후 줄바꿈없음");
        System.out.print("연결되어 보인다.");
        //형식화된 출력문(formatted print)
        System.out.println(); //한줄 줄바꿈 용도
        System.out.printf("%d\n", 30); // %d decimal 약자, 10진 정수(10진수로 된 정수)
        System.out.printf("%o\n", 30); // octal 8진 정수
        System.out.printf("%x\n", 30); // hexadecimal 16진 정수
        System.out.printf("%e\n", 300.0); // exponential 지수형 표현
        // \n은 줄바꿈해주는 특수문자이다.
        //자릿수 맞추기
        //오른쪽 정렬이 기본값
        System.out.printf("%5d\n", 123); // 5자릿수(공백으로 맞춤)
        System.out.printf("%05d\n", 123); // 5자릿수 (0으로 맞춤)
        // f : float,double 실수라는 의미. f를 생략하면 double
        System.out.printf("%05.2f\n", 123.45f); // 소숫점 둘째자리
    }
}
