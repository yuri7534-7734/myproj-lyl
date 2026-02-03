    //생성자 함수(Constructor)
    //    : 클래스가 생성될 때(new) 자동으로 호출되는 메소드
    //    : 메소드 이름은 클래스와 동일하고
    //    : 용도 - 필드(멤버변수)를 초기화할 때

    class Book {

        int price = 1000; //속성

        //생성자 함수는 public으로 시작
        //public 반환타입x 클래스이름( ) { }
        //클래스 이름과 똑같고, 반환 return이 없다면 생성자함수
        public Book(int price){
            this.price = price;
            System.out.println("생성자함수 자동호출됨");
        }
        void read() { //행동
            System.out.println("읽는다.");
        }
    }


public class ex26 {
    public static void main(String[] args) {
        Book book = new Book(2000); // 2000원으로 초기화하고 사용할 때
        System.out.println(book.price);

    }
}
