package com.leew.springboot_init.service;

import com.leew.springboot_init.dto.BoardDTO;
import com.leew.springboot_init.entity.BoardEntity;
import com.leew.springboot_init.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    public void save(BoardDTO boardDTO) {
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity); // Entity 클래스를 넘겨 줘야 함
        // save 함수는 Interface 내부에 구현되어 있음
    }
}
