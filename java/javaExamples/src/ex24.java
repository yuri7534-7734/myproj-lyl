   //싱글톤(Singleton)
   //  : 프로그램 안에서 유일한 클래스 객체
   //  : new를 통해 객체를 여러번 생성하는 구조이기 때문에
   //    같은 클래스의 객체가 여러개 있다면,
   //    데이터의 유지관리가 어렵다.
   //  : 스프링 프레임워크에서는 싱글톤을 자동으로 유지/관리함.
   //  : Bean(Spring안에서 객체)이 죽으면 부활시켜주고, 중복안되게.
   //  : 유일한 객체(싱글톤)이 필요한 이유
   //    - 유일한 정보를 저장/관리하기 위해
   // new로 못 만들게 막는다. -> 생성자를 private로
   // 대신 한 개 만들어둔 걸 꺼내 쓰게 한다 -> static 메소드로 getinstance() 제공
   class FishBun { //붕어빵
       int bunNo = 10;
   }
   class FishBunSingleton { //유일무이한 클래스 객체
      int bunNo = 30;
      private static FishBunSingleton singleton;   // 자기 자신의 싱글톤
                                                   // 초기값은 null이다.

       //getInstall 메소드를 통해서 싱글톤 객체를 얻어온다.
     static FishBunSingleton getInstance() { //JDK개발자 주로 사용하는 함수.

         if(singleton == null) {
             singleton = new FishBunSingleton(); // 처음 한번만 생성
         }

         return singleton; // 계속 돌려줌

     } //FishBunSingleton getInstance()
   } // 클래스 FishBunSingleton

   class YuriHouse {
    int money = 100;
    private static YuriHouse singleton;
    static YuriHouse getInstance() {
        if(singleton == null) {
            singleton = new YuriHouse();
        }
        return singleton;
    }
   }

public class ex24 {
    public static void main(String[] args) {

        //싱글톤
        FishBunSingleton sBun1 = FishBunSingleton.getInstance();
        FishBunSingleton sBun2 = FishBunSingleton.getInstance();
        System.out.println( "주소값 "+sBun1 );
        System.out.println( "주소값 "+sBun2 );
        sBun1.bunNo = 40;
        System.out.println(sBun1.bunNo); // 같은 힙영역의 싱글톤 객체 사용
        System.out.println(sBun2.bunNo); // 같은 힙영역의 싱글톤 객체 사용
        // 로그인 회원정보 : 계정이름, 프로필 이미지
        // 로그인, 메인화면, 회원정보, 마이페이지,QnA,Faq

        FishBun bun1 = new FishBun();
        FishBun bun2 = new FishBun();
        System.out.println(bun1);
        System.out.println(bun2);  //각각 다르기 때문에 싱글톤이 아님
        bun1.bunNo = 20;
        System.out.println(bun1.bunNo); // 20
        System.out.println(bun2.bunNo); // 10
        // 일반 객체는 일관된 데이터를 저장하기 어렵다.
        // non-static(dynamic)객체는 Heap영역에 저장되고,
        // GC(Garbage Collector)가 자동으로 메모리에서 회수한다.


    }
}
