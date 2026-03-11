package com.study.ExCourses;

import com.study.ExCourses.dto.RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final RegistrationRepository registrationRepository;
    private final RegistrationEntity registrationEntity;


    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/api/register")
    public String apiRegister(@RequestBody RequestDto dto) {
        Optional<RegistrationEntity> responseDto =
                registrationRepository.findById(dto.getRegistration_id());
        if(responseDto.isPresent()){

        }


        return "redirect:/";
    }
}
