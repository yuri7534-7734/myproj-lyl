public class ex07 {
    public static void main(String[] args) {
        //산술
        int i = 10;
        int j = 3;
        System.out.println( i + j ); // int + int => int
        System.out.println( i - j ); // int - int => int
        System.out.println( i * j ); // int * int => int
        // 나누기를 하면 몫과 나머지가 나옴.
        System.out.println( "몫 " +i / j ); // int / int => int
        System.out.println( i / (float)j ); // int / float => float
        System.out.println( "나머지 " +i % j ); // int % int => int

        //소숫점 없애기
        float k = 3.54f;
        //1. Math.floor 함수
        System.out.println(Math.floor(k));
        System.out.println(Math.round(k)); // 4
        System.out.println(Math.round(Math.floor(k))); // 3
        //2. (int) 형변환 연산자
        System.out.println((int)k);
        //연습문제
        int n = 123;
        //1. 일의 자릿수 3을 출력하시오.
        System.out.println( n % 10);
        //2. 십의 자릿수 2를 출력하시오.
        System.out.println(n %100 /10);
        //3. 백의 자릿수 1을 출력하시오.
        System.out.println((n/100));
        double d = 3.567;
        //4. 소숫점 첫째자리를 출력하시오.
        System.out.println("4번문제 답1 " +Math.round(Math.floor(d*10)%10));
        System.out.println("4번문제 답2 "+(int)(d*10)%10);
        System.out.println("4번문제 "+(d*10)%10);
        //출력예) 5
        //5. 소숫점 첫째자리에서 반올림하여 출력하시오.
        System.out.println("5번문제 답1 "+(Math.floor(d+0.5f)));
        System.out.println("4번문제 답1 "+(Math.round(d)));
        //출력예) 4.0
        //6. 소숫점 둘째자리에서 반올림하여 출력하시오.
        System.out.println(Math.floor(d*10+0.5f)/10); //Math.floor 보다는 Math.round가 더 정확하다
        System.out.println(Math.round(d*10)/10.0f);
        //출력예) 3.6


        //반올림하는 법
        //1. 0.5를 더한다.
        //2. (int)형변환한다. 소숫점 날림.
        // 예) 3.49 -> 3.99 -> 3

        //Math.floor 소숫점 첫째자리 내림(왼쪽에 있는 정수)
        //예) 3.01 ~ 3.99 -> 3.0
        //    -3.01 ~ -3.99 -> -4.0
        //    -----*
        System.out.println(Math.floor(-2.345f));

        double num = 12.3456;
        // 1.
        System.out.println((int)(num*100)%10);

        // 2.
        System.out.println((int)(num*1000)%10);

        // 3.
        System.out.println(Math.floor(num*100)/100);

        // 4.
        System.out.println(Math.floor(num*100));
    }
}
