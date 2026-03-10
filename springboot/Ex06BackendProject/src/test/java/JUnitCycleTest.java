import org.junit.jupiter.api.*;

public class JUnitCycleTest {
    @BeforeAll //전체 테스트를 시작하기 전에 1회 실행하므로 메서드는 static으로 선언
    static void beforeAll(){ //클래스 레벨 설정
        System.out.println("BeforeAll");
    }

    @BeforeEach //테스트 케이스를 시작하기 전마다 실행
    public void beforeEach() { //메서드 레벨 설정
        System.out.println("@BeforeEach");
    }
    //테스트 실행
    @Test
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test
    public void test3() {
        System.out.println("test3");
    }
    //메서드 레벨 정리
    @AfterAll //전체 테스트를 마치고 종료하기 전에 1회 실행하므로 메서드는 static으로 선언
    static void afterAll() {
        System.out.println("@AfterAll");
    }
    //클래스 레벨 정리
    @AfterEach // 테스트 케이스를 종료하기 전마다 실행
    public void afterEach() {
        System.out.println("@AfterEach");
    }

}
