package com.study.shop;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AgeController {

    @GetMapping("/")
    @ResponseBody
    public String test(){
        Age age = new Age();
        age.setAge(20);

        age.setName("LeeYuri");
        System.out.println("age의 나이는? = "+ age.getAge());
        System.out.println("age의 이름은? = "+ age.getName());

        return "<h3>나이와 이름이 뭐게요?</h3>";

    }


}
