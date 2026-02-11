import java.util.*;

public class ex58 {
    public static void main(String[] args) {
        // 맵(Map) : 키와 값으로 구성된 데이터 구조
        //         : 엔트리 객체를 저장한다.
        //         : 키는 중복 X, 값은 중복 O
        //         : key(문자열타입) - Value(객체, 기본데이터타입(8))
        //         : JSON, XML 데이터 연결이 쉽다.
        //         : -> 클래스도 JSON/XML과 오브젝트 매핑이 된다.
        //         : 데이터 바인딩(binding) 또는 직렬화(Serialization)
        //         : 자바 객체 -> JSON/XML문자열 (직렬화)
        //         : JSON/XML문자열 -> 자바 객체 (역직렬화)

        //ArrayList의 List 인터페이스의 구현체이므로, 업캐스팅이 된다.
        //List가 가진 더작은 기능도 충분하다고 생각하므로
        List<Integer> list = new ArrayList<>();
        //ArrayList<Integer> arrayList = new ArrayList<>();
        //Map가 HashMap의 부모클래스이므로 업캐스팅이 된다.
        Map<String, String> map = new HashMap<>();
        //HashMap<String, String> hashMap = new HashMap<>();
        //HashMap 순서대로 저장하지 않음.
        //key마다 hashCode가 다르다.
        map.put("username","hong");
        map.put("password","1234");
        System.out.println(map);

        //키로 값을 얻어온다.
        System.out.println(map.get("username")); //hong
        System.out.println(map.get("password")); //1234

        //전체 순회
        //일반 for문은 인덱스가 없으므로 불가.

        //향상된 for문
        Set<String> keys = map.keySet();
        System.out.println("keys 확인 : "+keys); //키 확인
        for(String key : keys){
            System.out.println("향상된 for문 사용 : "+map.get(key)); //순서가 없음
        }

        //이터레이터 이용
        Iterator<String> it = keys.iterator();
        while(it.hasNext()){
            String key = it.next();
            System.out.println("while문 사용 : "+ map.get(key));
        }

    }
}
