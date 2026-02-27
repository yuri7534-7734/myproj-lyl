package com.study.Ex11CounterDB;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class HtmlController {
    @Autowired //Counter 클래스를 빈으로 주입받고 객체 생성
    private Counter counter;

    @Autowired
    CountRepository countRepository;

    //GET("/")요청이 오면 -> DB에서 count 값을 읽어서 -> model에 담고 -> index.html에 보낸다.
    @GetMapping("/") // "/" 이 주소로 GET 요청오면 이 함수 실행
    public String index(Model model) { //컨트롤러에서 뷰(타임리프 html)로 데이터를 보내는 상자
        //화면(MVC)을 반환하는 방식

        //DB의 count 테이블의 모든 행을 조회(select)해서 CountEntity리스트로 가져오기 ( Repository 조회 )
        List<CountEntity> list = countRepository.findAll();
        CountEntity countEntity = list.get(0);
        model.addAttribute("count",countEntity.getCount());

        return "index";
    }

    @GetMapping("/plus")
    public String plus(Model model) {
        //counter.setCount(counter.getCount() + 1);
        //model.addAttribute("count", counter.getCount());

        //값이 없을 수 있기 때문에 list 대신 Optional 사용
        //DB에서 조회하면 항상 데이터가 있다는 보장이 없어서, 값 없으면 바로 예외 터진다.
        //값이 있으면 -> Member / 값이 없으면 -> 빈 Optional
        Optional<CountEntity> optional =countRepository.findById(1L); //.findById() : Optional<CountEntity>
        //ifPresent() : 값이 존재할 때만 실행(true)해라
        optional.ifPresent( countEntity -> {

            //존재한다면? 그 안의 count 꺼내서 출력
            Long count = countEntity.getCount();
            System.out.println("count = " + count);

            //조회한 값을 증가시키고 DB에 반영하는 과정
            countEntity.updateCount( count + 1 ); //엔티티 내부의 count 값을 증가시킨다. / setCount 보다는 ' updateCount ' 를 더 선호한다.
            //updateCount()
            //1. 엔티티의 변경 책임을 엔티티 안에 둔다.
            //2. 무분별한 값 변경 방지
            //3. 객체지향 설계 원칙 지키기
            //   = 이 객체는 스스로 상태를 변경한다.

            countRepository.save( countEntity ); //id가 없으면 -> insert / id가 이미 존재하면 -> update
                                                 //save( countEntity ) : insert( 새데이터 ) / update( 기존 데이터 수정 )
        } );

        return "redirect:/";
    }

    @GetMapping("/minus")
    public String minus(Model model) {
        counter.setCount(counter.getCount() - 1);
        model.addAttribute("count", counter.getCount());
        return "index";
    }

    @GetMapping("/api/plus")
    @ResponseBody
    public String api_plus() {
        counter.setCount(counter.getCount() + 1);
        return String.valueOf(counter.getCount());
    }

    @GetMapping("api/minus")
    @ResponseBody
    public String api_minus() {
        counter.setCount(counter.getCount() - 1);
        return String.valueOf(counter.getCount());
    }

}
