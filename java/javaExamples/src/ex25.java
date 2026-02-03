//연습문제 - 싱글톤 만들기
//클래스 이름 : TossAccount
//  필드(private) : 계좌번호(1234), 고객이름(홍길동), 잔액(1000), 이자율(년3%)
//  메소드 : 입금(+100), 출금(-100), 이자계산(1년후 잔액), 잔액조회
class TossAccount {
    private int AccountNumber = 1234;
    private String name = "홍길동";
    private int total = 1000; // long이나 double로 해도 된다.
    private double rate = 0.03;

    private static TossAccount singleton;
    static TossAccount getInstance(){
      if(singleton==null){
          singleton = new TossAccount();
      }
      return singleton;
    } //getInstance 싱글톤

    public int plus (int price){
        return total = total+price;
    };
    public int minus (int price){
        return total = total-price;
    };
    public int calculation (){
        System.out.println("이자율은 년 3% 입니다.");
         total = this.total*((int)(1 + this.rate));
        return total;
    };
    public int getTotal(){
        return total;
    }

    //Getter/Setter 메소드 자동 생성!

    //입금
    public void income(int money) {
        this.total += money;
    }
    //출금
    public void outcome(int money) {
        this.total -= money;
    }
    //이자율
    public void retotal() {
        this.total = this.total*((int)(1 + this.rate));
    }



    public static void setSingleton(TossAccount singleton) {
        TossAccount.singleton = singleton;
    }
} // TossAccount class

public class ex25 {
    public static void main(String[] args) {
        //싱글톤을 호출후
        TossAccount tossAccount = TossAccount.getInstance();
        //1. 입금 메소드 호출
        System.out.println("입금 : "+tossAccount.plus(100));
        System.out.println("잔액조회 : "+tossAccount.getTotal());
        //2. 출금 메소드 호출
        System.out.println("출금 : "+tossAccount.minus(100));
        System.out.println("잔액조회 : "+tossAccount.getTotal());
        //3. 이자계산은 이자율을 읽어서 참조한다.
        //   이자계산후 잔액 증가한다.
        System.out.println("이자계산 : "+tossAccount.calculation());
        //4. 최종 잔액을 출력한다.
        System.out.println("잔액조회 : "+tossAccount.getTotal());
        tossAccount.retotal();

    }
}