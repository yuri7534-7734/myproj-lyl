import java.util.Scanner;

public class pr01 {

    public static void main(String[] args) {
        //문제7 with teacher
//        Scanner scan = new Scanner(System.in);
//        System.out.println("숫자입력:");
//        int i = scan.nextInt();
//        System.out.println("i = " + i);
//
//        scan.next(); //초기화
//        System.out.println("문자열입력:");
//        String str = scan.next(); // nextLine()도 있음
//        System.out.println("str =" + str);

        //문제1
        String myString = "없음";
        System.out.println("myString = " + "Hello");

        System.out.println("Hello");

        //문제2
        System.out.println("Hello World");

        //문제3
        System.out.println("Hello\nWorld");

        //문제4
        System.out.println("'Hello'");

        //문제5
        System.out.println("\"Hello World\"");

        //문제6
        System.out.println("C:\\Download\\hello.java");
        
        //문제7
//        Scanner scan = new Scanner(System.in);
//        System.out.println("숫자입력:");
//        int i = scan.nextInt();
//        System.out.println("i = " + i);
//
//
//        scan.next();
//        //문제8
//        Scanner scan1 = new Scanner(System.in);
//        System.out.println("글자입력:");
//        String str = scan1.nextLine();
//        System.out.println(str);
//
        //문제9
//        Scanner scan2 = new Scanner(System.in);
//        System.out.println("실수입력:");
//        float i2 = scan2.nextFloat();
//        System.out.println(i2);

        //문제10
//        Scanner scan3 = new Scanner(System.in);
//        System.out.println("정수입력:");
//        Scanner scan4 = new Scanner(System.in);
//        System.out.println("정수입력2:");
//        int i3 = scan3.nextInt();
//        int i4 = scan4.nextInt();
//        System.out.printf(i3+" "+i4);
//
        //문제11
//        Scanner scan5 = new Scanner(System.in);
//        System.out.println("문자입력");
//        Scanner scan6 = new Scanner(System.in);
//        System.out.println("문자입력2");
//        String str1 = scan5.nextLine();
//        String str2 = scan6.nextLine();
//        System.out.println(str2+" "+str1);

        //문제12
//        Scanner scan7 = new Scanner(System.in);
//        System.out.println("단어입력:");
//        String str3 = scan7.nextLine();
//        System.out.println(str3);

        //문제13
//        Scanner scan8 = new Scanner(System.in);
//        System.out.println("실수입력:");
//        float i6 = scan8.nextFloat();
//        System.out.printf("%.2f", i6);
//        //또는 0.005f를 더하여, 100을 곱한 후 (int)로 타입변경후, 100으로 나누어도 된다.
         Scanner scan = new Scanner(System.in);
         float num = scan.nextFloat();
         num = (num+0.005f)*100;
         System.out.println(num);



        //문제14
//        Scanner scan9 = new Scanner(System.in);
//        System.out.println("hour(정수로 입력)");
//        Scanner scan10 = new Scanner(System.in);
//        System.out.println("minute(정수로 입력)");
//        int hour = scan9.nextInt();
//        int minute = scan10.nextInt();
//
//        System.out.println(hour+":"+minute);

        //문제15
//        Scanner year = new Scanner(System.in);
//        System.out.println("년도 입력");
//        Scanner month = new Scanner(System.in);
//        System.out.println("월 입력");
//        Scanner day = new Scanner(System.in);
//        System.out.println("일 입력");
//        int y = year.nextInt();
//        int m = month.nextInt();
//        int d = day.nextInt();
//        System.out.println(y+"."+m+"."+d);
    }
}

