//연습문제 - 클래스 상속
//
//부모클래스 - 나무     Wood
//          속성 가격 int price = 1000
//         행동 불탄다 burn()
//자식클래스 - 나무책상 WoodDesk
//          속성  color  "흰색"
//          행동  study   "공부를 한다."
//         - 나무의자 WoodChair
//          속성  color  "갈색"
//           행동  sit    "앉는다"
//자식클래스의 객체를 생성하고 속성과 행동을 출력하시오.
class Wood {
    int price = 1000;
    void burn() {
        System.out.println("불탄다.");
    }
}
class WoodDesk extends Wood {
    String color = "흰색";
    String study = "공부를 한다";

}
class WoodChair extends Wood {
    String color = "갈색";
    String sit = "앉는다";

}
public class ex29 {
    public static void main(String[] args) {
            WoodDesk woodDesk = new WoodDesk();
            woodDesk.burn();
            System.out.println("woodDesk의 color : " + woodDesk.color);
            System.out.println("woodDesk의 study : " + woodDesk.study);

            WoodChair woodChair = new WoodChair();
            woodChair.burn();
            System.out.println("woodChair의 color : " + woodChair.color);
            System.out.println("woodChair의 sit : " + woodChair.sit);
    }
}