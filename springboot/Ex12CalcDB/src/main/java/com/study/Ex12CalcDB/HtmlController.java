package com.study.Ex12CalcDB;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
//1.ViewController: @Controller - html파일을 반환
//2.RestController: @ResponseBody - 문자열(JSON)반환

//프론트엔드 html에서 서버에 값을 전달하기 위한 통신 방법 3가지
// 1. Form 태그 : GET, POST만 가능, submit 버튼을 통해
// 2. Form 태그 + JS Form객체의 submit()함수
// 3. JS fetch함수(Axois()함수)

// DB 접근(영속성 처리)을 위해 JPA를 사용하며, 보통 아래 구성요소로 계층을 나눈다.
// 1) Entity: DB 테이블(또는 뷰)과 매핑되는 영속 객체이며, 엔티티 간 관계(1:N 등)도 표현한다.
// 2) Repository: 엔티티에 대한 CRUD/조회 기능을 제공하는 인터페이스(Spring Data JPA가 구현체를 생성).
// 3) DTO: 요청/응답에 맞춘 데이터 전달용 객체로, 엔티티를 그대로 노출하지 않기 위해 사용한다.
//    하나의 엔티티에 대해 목적에 따라 여러 DTO가 존재할 수 있다.

@Controller
@RequiredArgsConstructor
public class HtmlController {


    @Autowired
    HistoryRepository historyRepository;


    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/calc-submit")
    public String calcSubmit(@RequestParam Integer num1, //HTTP 요청 파라미터를 명시적으로 받는다 = URL에서 전달된 값을 받는다. ex) ?num1=3&num25&calType=add
                             @RequestParam Integer num2, //Spring이 자동으로 변수에 넣는다.
                             @RequestParam String calType,
                             Model model) {
        //http 요청 파라미터/바디데이터를 가져오는 어노테이션은?
        //1.@RequestParam : 주로 Get방식
        //2.@RequestBody : 주로 POST, PUT, DELETE 방식
        //          -> 클래스나 Map으로 매핑(ORM)으로 받으면 편하다.
        //             @ModelAttribute
        // html의 name속성과 변수이름이 동일해야 매핑이 된다.
        System.out.println("num1 = " + num1); //값이 제대로 들어오는지 확인하는 용도
        System.out.println("num2 = " + num2);
        System.out.println("calType = " + calType);

        double result = 0;
        if (calType != null) { //calType이 있을 때만 계산
            int intNum1 = num1.intValue(); //intNum1에 num1의 문자열을 정수로 바꿔서 저장하기
            int intNum2 = num2.intValue();
            if (calType.equals("add")) {
                model.addAttribute("num1", intNum1);
                model.addAttribute("num2", intNum2);
                model.addAttribute("result", intNum1 + intNum2);
            }
            if (calType.equals("sub")) {
                model.addAttribute("num1", intNum1);
                model.addAttribute("num2", intNum2);
                model.addAttribute("result", intNum1 - intNum2);
            }
            if (calType.equals("mul")) {
                model.addAttribute("num1", intNum1);
                model.addAttribute("num2", intNum2);
                model.addAttribute("result", intNum1 * intNum2);
            }
            if (calType.equals("div")) {
                model.addAttribute("num1", intNum1);
                model.addAttribute("num2", intNum2);
                result = (double) intNum1 / intNum2;
                String formatted = String.format("%.2f", result);
                //String.format(형식, 값)

                model.addAttribute("result", formatted);
            }
        }

        //히스토리 테이블에 저장한다.
        //레파지토리 인터페이스의 save(e)함수를 사용한다.
        HistoryEntity entity = HistoryEntity.builder()
                .input1((Integer) num1)
                .input2((Integer) num2)
                .result((double) result)
                .op((String) calType)
                .build();
        historyRepository.save(entity);
        return "index";
    }


    @PostMapping("/calc-fetch")
    @ResponseBody
    public ResDto calcFetch(@RequestBody ReqDto dto) {//HTTP Body에 있는 데이터를 객체로 변환해서 넣어라.
        System.out.println(dto.getNum1());
        System.out.println(dto.getNum2());
        System.out.println(dto.getCalType()); //세개 다 문자열이니까
        String num1 = dto.getNum1();
        String num2 = dto.getNum2();
        String calType = dto.getCalType();

        if (dto != null) {
            if (calType.equals("add")) {
                int intNum1 = Integer.parseInt(num1); //덧셈을 할 수 있도록 문자열을 숫자로 변경
                int intNum2 = Integer.parseInt(num2);
                int result = intNum1 + intNum2;

                HistoryEntity entity = HistoryEntity.builder() //fetch방식의 DB에 저장
                        .input1((Integer) intNum1)
                        .input2((Integer) intNum2)
                        .result((double) result)
                        .op((String) calType)
                        .build();
                historyRepository.save(entity);

                ResDto resDto = new ResDto(); //응답을 할 수 있도록 ResDto를 가져온다.
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);
                // json 형태로 resDto가
                // { "status" : "ok", "result" : 4.0 } 보낸다.
                return resDto;
            }
            if (calType.equals("sub")) {
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = intNum1 - intNum2;

                HistoryEntity entity = HistoryEntity.builder()
                        .input1((Integer) intNum1)
                        .input2((Integer) intNum2)
                        .result((double) result)
                        .op((String) calType)
                        .build();
                historyRepository.save(entity);

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }
            if (calType.equals("mul")) {
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = intNum1 * intNum2;

                HistoryEntity entity = HistoryEntity.builder()
                        .input1((Integer) intNum1)
                        .input2((Integer) intNum2)
                        .result((double) result)
                        .op((String) calType)
                        .build();
                historyRepository.save(entity);

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }
            if (calType.equals("div")) {
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = (int) ((double) intNum1 / intNum2);

                HistoryEntity entity = HistoryEntity.builder()
                        .input1((Integer) intNum1)
                        .input2((Integer) intNum2)
                        .result((double) result)
                        .op((String) calType)
                        .build();
                historyRepository.save(entity);

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }

        }//dto!=null


        return null;
    }
}
