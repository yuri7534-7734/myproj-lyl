package com.study.Ex13VMDB;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class HtmlController {
    @Autowired
    private final ProductService productService;
    @Autowired
    private final ProductRepository productRepository;

    @GetMapping("/")
    public String main() {
        return "index";
    }
    
    //화면 목록보기
    @GetMapping("/listForm")
    public String listForm(Model model) {
        List<ProductDto> list = productService.findAll();
        model.addAttribute("list", list);
        return "listForm";
    }
    //업데이트 화면
    @GetMapping("/update")
    public String update(){
        return "updateItem";
    }

    //업데이트 보내기
    @GetMapping("/updateItem")
    public String updateItem(@RequestParam Integer product_no, Model model){
        ProductEntity entity = productRepository.findById( product_no )
                .orElseThrow( ()-> new IllegalArgumentException("상품이 없습니다."));
        ProductRequestDto dto = new ProductRequestDto(
                entity.getProduct_name(),
                entity.getProduct_price(),
                entity.getProduct_limit_date()
        );
        model.addAttribute("dto", dto);
        return "updateItem";
    }
    
    //삭제하기
    @GetMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam Integer product_no) {

        try {
            productRepository.deleteById(product_no);
        } catch (Exception e) {
            return "|<script>alert('상품 삭제 실패했습니다.') history.back();</script>|";

        }
        return "|<script>alert('상품 삭제 완료했습니다.'); location.href='/listForm'</script>|";

    }
    //추가하기 화면
    @GetMapping("/addForm")
    public String addForm() {
        return "addItem";
    }
    //진짜 추가하기
    @GetMapping("/getAddItem")
    @ResponseBody
    public String addItem(@RequestParam String product_name,
                          @RequestParam Integer product_price,
                          @RequestParam("product_limit_date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate product_limit_date,
                          Model model) {
        System.out.println("product_Name = " + product_name);
        System.out.println("product_Price =" + product_price);
        System.out.println("product_Date =" + product_limit_date);

        ProductEntity entity = ProductEntity.builder()
                .product_name(product_name)
                .product_price(product_price)
                .product_limit_date(product_limit_date)
                .build();
        try {
            productRepository.save(entity);
        } catch (Exception e) {
            return "|<script>alert('상품 등록 실패했습니다.') history.back();</script>|";

        }
        return "|<script>alert('상품 등록 완료했습니다.'); location.href='/listForm'</script>|";
    }

}
