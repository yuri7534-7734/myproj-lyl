import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JUnitTest {
    //JUnit은 테스트끼리 영향을 주지 않도록 각 테스트를 실행할 때 마다
    //테스트를 위한 실행 객체를 만들고 테스트가 종료되면 실행 객체를 삭제한다.
    @DisplayName("1+2는 3이다") //테스트이름
    @Test
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;
                   //assertEquals() : 검증 메서드
        Assertions.assertEquals(sum, a+b); //첫 번째 인수에는 기대하는 값, 두 번째 인수에는 실제로 검증할 값
        //Assertions : JUnit5 에서 제공하는 검증 도구
        //             내가 예상한 값이랑 실제 값이랑 같은지 확인해줘 라고 테스트에게 요청함.
    }

// 실패했을 때 Test
//
//    @DisplayName("1+2는 4이다") //테스트이름
//    @Test
//    public void junitFailTest() {
//        int a = 1;
//        int b = 2;
//        int sum = 4;
//        //assertEquals() : 검증 메서드
//        Assertions.assertEquals(sum, a+b); //첫 번째 인수에는 기대하는 값, 두 번째 인수에는 실제로 검증할 값
//        //Assertions : JUnit5 에서 제공하는 검증 도구
//        //             내가 예상한 값이랑 실제 값이랑 같은지 확인해줘 라고 테스트에게 요청함.
//    }
}
