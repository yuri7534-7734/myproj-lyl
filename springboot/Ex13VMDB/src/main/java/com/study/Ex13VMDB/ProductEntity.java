package com.study.Ex13VMDB;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Entity
@Getter
@Table(name="product")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_no")
    private Integer product_no;
    @Column(name="product_name")
    private String product_name;
    @Column(name="product_price")
    private Integer product_price;
    @Column(name="product_limit_date")
    private LocalDate product_limit_date;

    @Builder //선택적 매개변수가 있는 생성자를 빌더패턴으로 만들기
    public ProductEntity(String product_name, Integer product_price) {
        this.product_name = product_name;
        this.product_price = product_price;

    }
    public void edit(String product_name ,Integer product_price, LocalDate product_limit_date){
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_limit_date = product_limit_date;
    }

    //DTO to Entity
    public ProductEntity toEntity() {return null;}
}
