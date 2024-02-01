package com.leew.springboot_init.service;

import com.leew.springboot_init.dto.BoardDTO;
import com.leew.springboot_init.entity.BoardEntity;
import com.leew.springboot_init.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
