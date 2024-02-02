package com.leew.springboot_init.service;

import com.leew.springboot_init.dto.BoardDTO;
import com.leew.springboot_init.entity.BoardEntity;
import com.leew.springboot_init.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // Entity 클래스를 넘겨 줘야 함
        // save 함수는 Interface 내부에 구현되어 있음
    }

    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();

        // DB에서 올 때는 Entity로 온다. -> DTO로 변환해서 새 리스트에 옮겨담는다.
        List<BoardDTO> boardDTOList = new ArrayList<>();

        for (BoardEntity boardEntity : boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }

        // 옮겨 담은 리스트를 리턴해 준다.
        return boardDTOList;
    }

    @Transactional // JpaRepository를 상속한 인터페이스에 사용자가 추가로 구현한 메서드를 사용하는 경우에 Transactional Annotation을 넣어야 한다.
    public void updateHits(Long id) {
        // jpa가 제공하는 메서드는, select 조건, 정렬 등,,, 쿼리가 자동으로 만들어지는 형태
        // 특수한 목적을 가진 쿼리들은, 따로 정의할 필요가 있다. DB를 기준으로 직접 query 정의
        // "update board_table set board_hits = (board_hits) + 1 where id = ?" -> BoardRepository에서 정의
        boardRepository.updateHits(id);
    }

    public BoardDTO findByID(Long id) {
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();
            return BoardDTO.toBoardDTO(boardEntity);
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        /*
        boardRepository에 update를 위한 별도의 함수를 제공하지는 않는다.
        save 메소드로 update도 하고, insert도 한다.
        insert <-> save 기능을 구별하는 방식 : id가 있느냐 없느냐 // 있다면 update를 하는 방식으로 JPA가 작동

        */
        BoardEntity boardEntity = BoardEntity.toUpdateEntity(boardDTO); // update를 위한 메소드를 호출
        boardRepository.save(boardEntity); // Entity를 DB로 전달해야 DB의 값으로 저장됨

        // 상세 조회 값을 넘겨줘야 하기 때문에(리턴 값이 BoardDTO이다.)
        return findByID(boardDTO.getId()); // 위 findByID 함수 호출하여, 다시 상세 페이지에 담기 위한 객체를 담는다.
    }
}
