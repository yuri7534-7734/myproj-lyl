package com.study.Ex15Board.service;

import com.study.Ex15Board.domain.board.Board;
import com.study.Ex15Board.domain.board.BoardRepository;
import com.study.Ex15Board.dto.BoardResponseDto;
import com.study.Ex15Board.dto.BoardSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

//controller와 DB 사이에서 일을 처리해주는 중간 관리자
//게시글(Board) 엔티티들을 DB에서 전부 꺼내서
//화면/응답에 쓰기 좋은 DTO 리스트로 바꿔서 반환하는 코드
@Service //서비스(비즈니스 로직) 담당
@RequiredArgsConstructor //final 필드( 또는 @NonNull 필드)를 매개변수로 받는 생성사 자동 생성
public class BoardService {

    @Autowired //boardRepository 빈을 찾아서 주입
    private final BoardRepository boardRepository; //DB접근 담당 레포지토리


    //@RequiredArgsConstructor 썼다면 사실 안써도 됨.

    //@Transactional
    //트랜잭션 처리 - 단위작업 처리시 중간에 오류 발생시 Rollback처리해준다.
    //            - 정상 처리되면 Commit(물리적 파일에 기록)됨.
    // Rollback은 Insert, Update, Delete에만 필요. Select는 필요없음.

    @Transactional(readOnly = true)           //BoardResponseDto 리스트
    public List<BoardResponseDto> findAll() { //게시글 전체목록 조회 API/화면에서 사용

        //DB에서 가져온 원본 게시글 리스트
        List<Board> list = boardRepository.findAll(); //JPA Repository가 기본 제공하는 메소드
                                                      //SQL에서 SELECT * FROM board;
                                                      //결과는 엔티티 객체(Board)리스트 반환
        //엔티티 리스트 -> DTO 리스트로 변환(Stream)
        //stream() 메소드 사용 : List<Board> -> List<DTO> / 반복 처리하기 / for문 돌릴 준비
        return list.stream().map(BoardResponseDto::new) //BoardResponseDto::new 생성자 참조문법
                .collect(Collectors.toList()); //다시 리스트로 모으기
    }


    //게시글 저장하기
    @Transactional
    public Long save(final BoardSaveRequestDto dto){
        Board entity = boardRepository.save(dto.toEntity());
        return entity.getBoardIdx();
    }
    //글쓰기 하고 나서 실제 게시글이 있는지 확인용도
    @Transactional(readOnly = true)
    public boolean existsById(Long boardIdx){
        boolean isFound = boardRepository.existsById(boardIdx);
        return isFound;
    }
    //게시글 DB인덱스로 게시글 정보 가져오기
    @Transactional(readOnly = true)
    public BoardResponseDto findById(Long boardIdx){
        Board entity = boardRepository.findById(boardIdx)
                .orElseThrow( () -> new IllegalArgumentException("없는 글인덱스입니다.") );
              // Optional클래스 함수 .orElseThrow( 람다식 ) : null이면 람다 실행함.
        return new BoardResponseDto(entity);
    }

    //조회수 업데이트하기
    @Transactional
    public void updateHit(final Long boardIdx, final Integer hit){
        //BEGIN TRANSACTION
        Board entity = boardRepository.findById(boardIdx)
                .orElseThrow( () -> new IllegalArgumentException("없는 글인덱스입니다.") );

        //엔티티 인스턴스의 멤버 변수(필드)를 수정하면, 자동으로 save된다.
        //repo의 save함수를 별도로 사용하지 않아도 됨.
        entity.updateHit( hit );
        //COMMIT(ROLLBACK)이 자동 발생한다.
    }

    //게시글 업데이트하기
    @Transactional
    public boolean update(final Long boardIdx, final BoardSaveRequestDto dto){
        Board entity = boardRepository.findById(boardIdx)
                .orElseThrow( () -> new IllegalArgumentException("없는 글인덱스입니다.") );

        entity.update(dto.getBoardName(), dto.getBoardTitle(),
                dto.getBoardContent(),dto.getBoardHit());

        return true;
    }

    //게시글 지우기
    @Transactional
    public void delete(final Long boardIdx){
        Board entity = boardRepository.findById(boardIdx)
                .orElseThrow( () -> new IllegalArgumentException("없는 글인덱스입니다.") );

        boardRepository.delete(entity); //delete는 return 값이 없다
                                        // 결과 데이터를 돌려주기보다 DB에 삭제 요청을 수행하는 게 목적이기 때문에 Void 사용
    }                                   // 예외 안터지면 정상 처리된 것

}//Class
