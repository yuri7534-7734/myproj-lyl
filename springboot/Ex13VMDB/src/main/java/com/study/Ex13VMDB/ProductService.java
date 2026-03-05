package com.study.Ex13VMDB;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//ProductService가 하는 일
//controller와 DB 사이에서 일을 처리해주는 중간 관리자
//ProductEntity에서 엔티티들을 DB에서 전부꺼내서
//화면/응답에 쓰기 좋은 DTO 리스트로 바꿔서 반환하는 코드

@Service
@RequiredArgsConstructor
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    //@Transactional
    //트랜잭션 처리 - 단위작업 처리시 중간에 오류 발생 시 Rollback처리해준다.
    //            - 정상 처리되면 Commit(물리적 파일에 기록)된다.
    // Rollback은 Insert, Update, Delete에만 필요. Select는 필요없음.
    @Transactional(readOnly = true)
    public List<ProductDto> findAll() { //ProductDto 리스트

        //DB에서 가져온 원본 게시글 리스트
        List<ProductEntity> list = productRepository.findAll();

        //stram() 메소드 사용 : List<ProductEntity> -> List<ProductDto>
        return list.stream().map(ProductDto::new)
                .collect(Collectors.toList());
    }
    //상품 저장하기
    @Transactional
    public Integer save(final ProductRequestDto dto){
        ProductEntity entity = productRepository.save(dto.toEntity());
        return entity.getProduct_no();
    }

    //상품 지우기
    @Transactional
    public void delete(final Integer product_no){
        ProductEntity entity = productRepository.findById(product_no)
                .orElseThrow(()-> new IllegalArgumentException("내용이 없습니다."));

        productRepository.delete(entity);
    }


}
