package com.study.shop;


import lombok.RequiredArgsConstructor;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class HtmlController {

    private final ItemRepository itemRepository;

    @GetMapping("/list")
    public String list(Model model){
        List<Item> result = itemRepository.findAll();
        model.addAttribute("items", result);
        var a = new Item();
        System.out.println(a.toString());
        return "list";
    }

    @GetMapping("/write")
    public String write(){

        return "write";
    }

    @PostMapping("/add")
    public String addPost(@ModelAttribute Item item){

        System.out.println(item);
        itemRepository.save(item);

        return "redirect:/list";
    }

    @GetMapping("/detail/{id}")
    public String detail(){
        //비어있을 수도 있고 있을 수도 있습니다.
        Optional<Item> result = itemRepository.findById(1L);
        if(result.isPresent()) {
            System.out.println(result.get());
        }


        return "detail";
    }

    }//class

