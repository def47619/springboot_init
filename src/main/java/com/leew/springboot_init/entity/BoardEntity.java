package com.leew.springboot_init.entity;

import com.leew.springboot_init.dto.BoardDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name="board_table")
public class BoardEntity extends BaseEntity {
    // BaseEntity 상속 : LocalDateTime 객체의 멤버변수를 같이 가지게 된다.

    @Id // pk 컬럼 지정, 필수
    @GeneratedValue(strategy= GenerationType.IDENTITY) // MySQL 기준, AUTO_INCREMENT 기능
    private Long id;

    @Column(length=20, nullable=false) // 값의 길이를 20으로 제한
    private String boardWriter;

    @Column // default : 크기 255, null 가능
    private String boardPass;

    @Column
    private String boardTitle;

    @Column(length=500)
    private String boardContents;

    @Column
    private int boardHits;

    // 자기 자신 클래스를 반환
    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();

        // id는 자동 생성, auto_increment이므로, 생성하지 않는다.
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0); // default hits 값은 0이다.
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        // ID가 있는 값을 update하는 것이기 때문에, id를 가져와서 저장해야 한다. 아니면, auto-increment로 새 값으로 insert됨

        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setBoardPass(boardDTO.getBoardPass());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContents(boardDTO.getBoardContents());
        boardEntity.setBoardHits(0); // default hits 값은 0이다.
        return boardEntity;
    }
}
