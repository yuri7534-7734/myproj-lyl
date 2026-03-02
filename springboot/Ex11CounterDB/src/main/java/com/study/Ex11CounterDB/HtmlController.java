package com.study.Ex11CounterDB;
// 전체흐름
// 브라우저 요청, HTML
// Controller
// Repository ( DB 접근 )
// Entity ( DB 테이블 )
// DB

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class HtmlController { //브라우저 요청 시 여기로 들어온다.

    @Autowired //Counter 클래스를 빈으로 주입받고 객체 생성
    private Counter counter;

    @Autowired
    CountRepository countRepository;

    //GET("/")요청이 오면 -> DB에서 count 값을 읽어서 -> model에 담고 -> index.html에 보낸다.
    @GetMapping("/") //@@ 메인페이지 "/" 이 주소로 GET 요청오면 이 함수 실행
    public String index(Model model) { //컨트롤러에서 뷰(타임리프 html)로 데이터를 보내는 상자
        //화면(MVC)을 반환하는 방식

        //DB의 count 테이블의 모든 행을 조회(select)해서 CountEntity리스트로 가져오기 ( Repository 조회 )
        List<CountEntity> list = countRepository.findAll(); //SELECT * FROM count;
        CountEntity countEntity = list.get(0); //첫번째 데이터 가져오기
        model.addAttribute("count",countEntity.getCount()); //HTML에 전달하기 -> ${ count }

        return "index"; //타임리프
    }

    @GetMapping("/plus")
    public String plus(Model model) {
        //counter.setCount(counter.getCount() + 1);
        //model.addAttribute("count", counter.getCount());

        //값이 없을 수 있기 때문에 list 대신 Optional 사용
        //DB에서 조회하면 항상 데이터가 있다는 보장이 없어서, 값 없으면 바로 예외 터진다.
        //값이 있으면 -> Member / 값이 없으면 -> 빈 Optional
        Optional<CountEntity> optional = countRepository.findById(1L); //데이터 조회, SELECT * FROM count WHERE id = 1;
        //ifPresent() : 값이 존재할 때만(true) 실행해라
        optional.ifPresent( countEntity -> {

            Long count = countEntity.getCount(); //count 꺼내기
            System.out.println("count = " + count);

            //조회한 값을 증가시키고 DB에 반영하는 과정
            countEntity.updateCount( count + 1 ); //엔티티 내부의 count 값을 증가시킨다. / setCount 보다는 ' updateCount ' 를 더 선호한다.
            //updateCount()
            //1. 엔티티의 변경 책임을 엔티티 안에 둔다.
            //2. 무분별한 값 변경 방지
            //3. 객체지향 설계 원칙 지키기
            //   = 이 객체는 스스로 상태를 변경한다.

            //마지막 DB저장
            countRepository.save( countEntity ); //id가 없으면 -> insert / id가 이미 존재하면 -> update
                                                 //save( countEntity ) : insert( 새데이터 ) / update( 기존 데이터 수정 )
        } );

        return "redirect:/"; //다시 메인페이지
    }

    @GetMapping("/minus")
    public String minus(Model model) { //메모리 값(변수)을 증가
       Optional<CountEntity> optional = countRepository.findById(1L);
       optional.ifPresent( countEntity -> {
           Long count = countEntity.getCount();
           System.out.println("count = " + count);

           countEntity.updateCount(count-1);
           countRepository.save(countEntity);


       });
        return "redirect:/";
    }

    @GetMapping("/api/plus")
    @ResponseBody
    public String api_plus() { //메모리 값(변수)을 증가
        //counter.setCount(counter.getCount() + 1);
        //return String.valueOf(counter.getCount());

        CountEntity countEntity = countRepository.findById(1L).get(); //DB에서 count_no = 1인 데이터를 가져와라
                                                                      //SELECT * FROM count WHERE count_no = 1 그리고 반환되는 것은 CountEntity 객체
        Long count = countEntity.getCount();
        System.out.println("count = " + count);

        countEntity.updateCount( count +1L );
        countRepository.save(countEntity);

        return String.valueOf( count + 1L); // "1"형식의 문자열로 응답한다.
        //String.valueOf() : 숫자 12 -> 문자열 "12" #변환 함수
    }

    @GetMapping("api/minus")
    @ResponseBody
    public String api_minus() {
        //counter.setCount(counter.getCount() - 1);
        //return String.valueOf(counter.getCount());

        CountEntity countEntity = countRepository.findById(1L).get();
        Long count = countEntity.getCount();
        System.out.println("count = " + count);

        countEntity.updateCount(count-1L);
        countRepository.save(countEntity);

        return String.valueOf(count-1L);

    }

}

//총정리
// plus() : DB count 증가
// api_plus() : 메모리 count 증가

//DB 방식 : Repository, Entity, DB 저장

//API 방식 : JS fetch, @ResponseBody, AJAX 통신