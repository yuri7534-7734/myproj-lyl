package com.study.Ex13VMDB;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
public class ProductRequestDto {
    private String product_name;
    private Integer product_price;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate product_limit_date;

    @Builder
    public ProductRequestDto(String product_name, Integer product_price, LocalDate product_limit_date) {

        this.product_name = product_name;
        this.product_price = product_price;
        this.product_limit_date = product_limit_date;
    }
    //DTO to Entity
    public ProductEntity toEntity() {
        return ProductEntity.builder()
                .product_name(product_name).product_price(product_price).product_limit_date(product_limit_date).build();
    }

}
