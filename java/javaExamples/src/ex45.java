public class ex45 {
    public static void main(String[] args) {

        try {
            myFunc();
        } catch (Exception e) { //다형성으로 들어옴
            //다운캐스팅을 통해서 MyException 객체에 접근할 수 있다.
            MyException me = (MyException)e;
            me.printMessage();
        }

    }
    static void myFunc() throws Exception{
        // 사용자 정의 예외
        MyException e = new MyException();
        e.message = "사용자 정의 예외입니다.";
        throw e; // 예외 강제 발생

    }
}

        // 일반적인 Exception외에 추가적인 기능 추가 가능
class MyException extends Exception {
    String message = "";
    public void printMessage(){
        System.out.println(this.message);
    }
}
