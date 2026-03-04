package com.study.Ex13VMDB;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HtmlController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @GetMapping("/listForm")
    public String listForm(Model model){
        List<ProductDto> list = productService.findAll();
        model.addAttribute("list",list);
        return "listForm";
    }
}
