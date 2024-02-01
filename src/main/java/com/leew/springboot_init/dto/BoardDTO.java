package com.leew.springboot_init.dto;

import com.leew.springboot_init.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

// DTO(Data Transfer Object), VO, Bean, Entity
@Getter // Lombok
@Setter
@NoArgsConstructor // 기본 생성자
@AllArgsConstructor // 모든 필드를 매개변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private String boardWriter;
    private String boardPass;
    private String boardTitle;
    private String boardContents;
    private int boardHits;
    private LocalDateTime boardCreatedTime;
    private LocalDateTime boardUpdatedTime;

    @Override
    public String toString() {
        return "BoardDTO{" +
                "id=" + id +
                ", boardWriter='" + boardWriter + '\'' +
                ", boardPass='" + boardPass + '\'' +
                ", boardTitle='" + boardTitle + '\'' +
                ", boardContent='" + boardContents + '\'' +
                ", boardHits=" + boardHits +
                ", boardCreatedTime=" + boardCreatedTime +
                ", boardUpdatedTime=" + boardUpdatedTime +
                '}';
    }

    public static BoardDTO toBoardDTO(BoardEntity boardEntity) {
        BoardDTO boardDTO = new BoardDTO();

        // 옮겨담기
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardWriter(boardEntity.getBoardWriter());
        boardDTO.setBoardPass(boardEntity.getBoardPass());
        boardDTO.setBoardTitle(boardEntity.getBoardWriter());
        boardDTO.setBoardContents(boardEntity.getBoardContents());
        boardDTO.setBoardHits(boardEntity.getBoardHits());
        boardDTO.setBoardCreatedTime(boardEntity.getCreatedTime());
        boardDTO.setBoardUpdatedTime(boardEntity.getUpdatedTime());

        return boardDTO;
    }
}
