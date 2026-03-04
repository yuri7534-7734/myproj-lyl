package com.study.Ex13VMDB;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Integer product_no;
    private String product_name;
    private Integer product_price;
    private LocalDate product_limit_date;

    public ProductDto (ProductEntity entity) {
        this.product_no = entity.getProduct_no();
        this.product_name = entity.getProduct_name();
        this.product_price = entity.getProduct_price();
        this.product_limit_date = entity.getProduct_limit_date();
    }

}
