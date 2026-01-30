import java.util.Random;

public class ex10 {
    public static void main(String[] args) {
        //조건문 = JS와 문법이 거의 유사
        // 1. 단순 if문
        if (10 < 20) {
            System.out.println("10은 20보다 작음");
        }
        //실행문이 한 줄일 때는 { } 중괄호를 생략 가능
        //(조건문,반복문)
        if (10 < 20)
            System.out.println("111");

        // 2. if else문
        if (10 > 20) {
            System.out.println("10은 20보다 크다");
        } else {
            System.out.println("10은 20보다 작음");
        }
        // 3. if else if문
        int score = 90;
        if (score > 90) {
            System.out.println("90점 이상");
        } else if (score >= 80) {
            System.out.println("80점 이상");
        } else { // 그 외의 수
            System.out.println("그 외의 수");
        }

        // 4. 중첩if문(if문 안의 if문(1,2,3))
        if(true){//조건1
            if(false) {//조건2
                //조건1이 참이고, 조건2가 참일 때
        }else if(true) {//조건3
                //조건1이 참이고
                //조건2가 거짓이고
                //조건3이 참일 때
            }
        } //if

        //자바에서 랜덤수 발생
        //1. Math.random() 0.0 ~ 0.99999...
        System.out.println((int)(Math.random() * 6));
        //2. Random 클래스 import
        Random rand = new Random();
        int num = rand.nextInt(6);
        System.out.println("num = " + num);
        //   rand.nextInt(6) 0 ~ 5의 랜덤정수 발생

        //연습문제
        //1. 철수와 영희가 주사위 놀이를 하고 있다.
        // 주사위 2개를 던져서,
        // 두개 다 짝수가 나오면 철수 승!
        // 두개 다 홀수가 나오면, 영희 승!
        // 그외의 경우는 무승부! 이다.
        // 게임의 결과를 출력하시오.
        System.out.println((int)(Math.random() * 6 ));
        int ran1 = (int)(Math.random()*6)+1;
        System.out.println("첫번째 숫자 = " + ran1);
        int ran2 = (int)(Math.random()*6)+1;
        System.out.println("두번째 숫자 = " + ran2);
        if ( ran1%2==0 && ran2%2==0) {
            System.out.println("철수 승");
        }else if(ran1%2==1 && ran2%2==1) {
            System.out.println("영희 승");
        } else {
            System.out.println("무승부");
        }
        //2.
        //철수와 영희을 주사위게임을 하고 있다.
        //주사위 2개를 철수가 던지고,
        //주사위 2개를 영희도 던진다.
        //게임룰 : 첫번째 주사위는 십의 자릿수로하고,
        //        두번째 주사위는 일의 자릿수로 해서,
        // 더 높은 점수를 가진 사람이 승리한다.
        //출력값 예시 :
        //        철수 주사위1 수 : 1
        //        철수 주사위2 수 : 3
        //        철수의 점수는 13
        //        영희 주사위1 수 : 3
        //        영희 주사위2 수 : 4
        //        영희의 점수는 34
        //        영희 승!
        int ranCs1 = (int)((Math.random()*6)+1)*10;
        int ranCs2 = (int)((Math.random()*6)+1);
        int rstCs = ranCs1+ranCs2;
        System.out.println("철수점수 = " + rstCs);
        int ranYh1 = (int)((Math.random()*6)+1)*10;
        int ranYh2 = (int)((Math.random()*6)+1);
        int rstYh = ranYh1+ranYh2;
        System.out.println("영희점수 = " + rstYh);
        if ( rstCs > rstYh) {
            System.out.println("철수 승");
        } else if (rstCs < rstYh) {
            System.out.println("영희 승");
        } else {
            System.out.println("무승부");
        }

        // 변수, 함수, 클래스 이름 짓기
        // Java 계열에서는 카멜(Camel) : 예) mySundayEventPoint 길게 풀어서...
        // JS, Python 스네이크(Snake) : 예) my_Snake





    } //main
} //class
