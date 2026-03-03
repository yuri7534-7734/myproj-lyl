package com.study.Ex12CalcDB;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HtmlController {
    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/calc-submit")
    public String calcSubmit(@RequestParam Number num1, //URL에서 전달된 값을 받는다. ex) ?num1=3&num25&calType=add
                             @RequestParam Number num2, //Spring이 자동으로 변수에 넣는다.
                             @RequestParam String calType,
                             Model model){
        System.out.println("num1 = " + num1); //값이 제대로 들어오는지 확인하는 용도
        System.out.println("num2 = " + num2);
        System.out.println("calType = " + calType);

        if(calType != null){ //calType이 있을 때만 계산
            int intNum1 = num1.intValue(); //intNum1에 num1의 문자열을 정수로 바꿔서 저장하기
            int intNum2 = num2.intValue();
            if(calType.equals("add")){
                model.addAttribute("num1",intNum1);
                model.addAttribute("num2",intNum2);
                model.addAttribute("result",intNum1+intNum2);
            }
            if(calType.equals("sub")){
                model.addAttribute("num1",intNum1);
                model.addAttribute("num2",intNum2);
                model.addAttribute("result",intNum1-intNum2);
            }
            if(calType.equals("mul")){
                model.addAttribute("num1",intNum1);
                model.addAttribute("num2",intNum2);
                model.addAttribute("result",intNum1*intNum2);
            }
            if(calType.equals("div")){
                model.addAttribute("num1",intNum1);
                model.addAttribute("num2",intNum2);
                double result = (double) intNum1 / intNum2;
                String formatted = String.format("%.2f",result);
                //String.format(형식, 값)

                model.addAttribute("result",formatted);
            }
        }
        return "index";
    }

    @PostMapping("/calc-fetch")
    @ResponseBody
    public ResDto calcFetch(@RequestBody ReqDto dto){
        System.out.println(dto.getNum1());
        System.out.println(dto.getNum2());
        System.out.println(dto.getCalType());
        String num1 = dto.getNum1();
        String num2 = dto.getNum2();
        String calType = dto.getCalType();

        if(dto != null) {
            if(calType.equals("add")){
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = intNum1 + intNum2;

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }
            if(calType.equals("sub")){
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = intNum1 - intNum2;

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }
            if(calType.equals("mul")){
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = intNum1 * intNum2;

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }
            if(calType.equals("div")){
                int intNum1 = Integer.parseInt(num1);
                int intNum2 = Integer.parseInt(num2);
                int result = (int)((double)intNum1 / intNum2);

                ResDto resDto = new ResDto();
                resDto.setStatus("ok"); //응답상태 알려주기
                resDto.setResult(result);

                return resDto;
            }

        }//dto!=null

        return null;
    }
}
