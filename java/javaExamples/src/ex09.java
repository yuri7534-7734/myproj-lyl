import java.util.Scanner;
//import하는 이유? 컴파일(번역,패키징)할 때 관련코드를 포함시킨다.
//  - 실행파일(용량) 크기를 최소화하기 위해서.
//   예) 낚시하러 가는 데 항공모항? 낚시(배+낚시도구)
 // Math 패키지(클래스) import안해도 사용 가능 - 자주 사용하기에 도구처럼 객체를 만들어놓음
public class ex09 {
    public static void main(String[] args) {
        //Scanner클래스 함수들 : 콘솔로 문자열 입력받을 때
        // nextLine() : 문자열을 입력받되 엔터칠때까지(공백포함)
        // next() : 문자열의 공백까지만 입력받음.
        // nextInt() : 문자열을 받아서 정수로 반환해줌.
        // nextFloat() :             실수로 반환해줌.
        // nextDouble() :            실수로 반환해줌.

        //클래스이름 객체이름(인스턴스) = new 클래스이름( 인자 = 매개변수 )
        Scanner scan = new Scanner(System.in);
        //웰컴문구(입력문구)
        System.out.print("입력하세요:");
        String str1 = scan.nextLine(); //엔터치면 입력 끝.
        System.out.println("str1 = " + str1);

        System.out.println("입력하세요2");
        String str2 = scan.next();
        // 공백까지만 입력받음
        System.out.println("str2 = " + str2);
        //java.util.InputMismatchException 오류
        //next 함수에 저장된 버퍼메모리가 정리되지 않아서이다.
        //해결방법 : nextLine()함수를 그냥 한번 실행해주면 된다.
        scan.nextLine();
        
        System.out.println("입력하세요3");
        int num1 = scan.nextInt();
        System.out.println("num1 = " + num1);

        System.out.println("입력하세요4:");
        double num2 = scan.nextDouble();
        System.out.println("num2 = " + num2);

        scan.close(); //scan객체가 사용하는 리소스(메모리 등)을 정리한다.
        //자바에서는 gc(Gabege Collector)가 사용하지 않는 객체를
        //자동으로 정리해 준다.(dealloc 메모리해제)


        // Java에서 오류(예외)가 나면, 맨처음과 맨마지막 메세지가 중요!
//        Exception in thread "main" java.util.InputMismatchException
//        at java.base/java.util.Scanner.throwFor(Scanner.java:947)
//        at java.base/java.ustil.Scanner.next(Scanner.java:1602)
//        at java.base/java.util.Scanner.nextInt(Scanner.java:2267)
//        at java.base/java.util.Scanner.nextInt(Scanner.java:2221)
//        at ex09.main(ex09.java:28)
    }
}