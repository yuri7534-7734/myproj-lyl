//연습문제
//클래스이름 : Desk
//속성 - 필드(멤버변수) : color = "갈색"
//행동 - 메소드(함수) : work() "일한다"로 출력
//    - 생성자 함수에 color 값을 "흰색" 초기화 하여,
//      객체생성하시오.
class Desk1 {
    String color = "갈색";

    //생성자함수 자동생성
//    public Desk1(String color) {
//        this.color = color;
//    }
    public Desk1(String color){
        this.color = color;
        System.out.println("컬러가 "+this.color+" 변경되었습니다.");
    }
    void work() {
        System.out.println("일한다");
    }

    public String getColor() {
        return color;
    }
//    Setter 함수와 비슷
//    public void setColor(String color) {
//        this.color = color;
//    }
}
 class Korean1{
    String nation = "대한민국";
    String name;
    String ssn;
            public Korean1(String name, String ssn){
                this.name = name;
                this.ssn = ssn;
            }
 }



public class ex27 {
    public static void main(String[] args) {


        Desk1 desk = new Desk1("흰색");
        desk.work();
        System.out.println(desk.color+" 입니다.");

        Korean1 kor1 = new Korean1("이유리", "990610");
        System.out.println(kor1.name);
    }
}
